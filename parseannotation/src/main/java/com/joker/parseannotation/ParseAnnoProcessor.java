package com.joker.parseannotation;

import com.google.auto.common.MoreElements;
import com.google.auto.service.AutoService;
import com.joker.annotation.XyBindView;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

@AutoService(Processor.class)
public class ParseAnnoProcessor extends AbstractProcessor {

    //getSupportedAnnotationTypes 方法的内容很简单，返回一个该注解处理器需要处理的注解的字符串集合，我的理解他是一个注册过程。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationNames=new LinkedHashSet<>();
        annotationNames.add(XyBindView.class.getCanonicalName());
        return annotationNames;
    }

    //process 方法主要就是解析指定注解上的所有信息。
    //参数 RoundEnvironment roundEnv ：表示当前或是之前的运行环境，可以通过该对象查找找到的注解。拿到所有的注解对象元素以后，遍历所有注解元素
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //获取所有带有BindView注解的元素
        Set<? extends Element> elements=roundEnvironment.getElementsAnnotatedWith(XyBindView.class);
        // 按照Activity 对 XyBindView 注解的元素进行分类----key:activityName
        Map<String, List<VariableElement>> activityElementMap=new HashMap<>();
        for(Element element:elements){
            //VariableElement 是注解在成员变量身上的元素
            VariableElement variableElement= (VariableElement) element;
            //返回这个元素的最里层元素， 通常我们将注解写在了成员变量上， 所以这里返回的就是类元素
            TypeElement enclosingElement= (TypeElement) variableElement.getEnclosingElement();
            String packageName= MoreElements.getPackage(enclosingElement).getQualifiedName().toString();
            System.out.print("BindViewGenerator  = "+packageName);
            //返回此类型元素的完全限定名称,即类名全路径
            String activityName = enclosingElement.getQualifiedName().toString();
            //将一个类上的所有的ViewBind注解元素都放在一起
            //按照activity的包路径进行分类
            List<VariableElement> elementList = activityElementMap.get(activityName);
            if (elementList == null){
                elementList = new ArrayList<>();
                activityElementMap.put(activityName,elementList);
            }
            elementList.add(variableElement);
        }
        // 生成java 文件
        //按照类（Activity）进行遍历，生成对应的xxx_BindViewing类
        Set<Map.Entry<String, List<VariableElement>>> entries = activityElementMap.entrySet();
        for (Map.Entry<String, List<VariableElement>> entry : entries) {
            // 获取到要解析注解对应的类名
            String activityName = entry.getKey();
            //获取到simpleName
            String substring = activityName.substring(activityName.lastIndexOf(".")+1, activityName.length());
            //创建类 我们需要创建的类和我们的Activity在同一个包下，类名是_ViewBinding  这是一个统一的规则
            //JavaPoet提供了(TypeSpec)用于创建类或者接口，(FieldSpec)用来创建字段，(MethodSpec)用来创建方法和构造函数，(ParameterSpec)用来创建参数，(AnnotationSpec)用于创建注解。
            TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(substring+"_ViewBinding")
                    .addModifiers(Modifier.PUBLIC);// 创建的类是public
            //创建构造方法
            MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder()// 构造方法
                    .addModifiers(Modifier.PUBLIC)// 创建的方法是public
                    .addParameter(TypeVariableName.get(activityName),"target");// 需要传入的方法
            // 在方法内部加入findViewById，并把得到的值传给对应的Activity
            List<VariableElement> elementList = entry.getValue();
            String packageName = MoreElements.getPackage(elementList.get(0)).getQualifiedName().toString();
            //拿到类里面所有的注解元素  （标记成员变量的注解元素VariableElement）
            for (VariableElement element : elementList) {
                //得到注解的变量名
                String name = element.getSimpleName().toString();
                //得到变量名的类型
                TypeMirror typeMirror = element.asType();
                // 得到变量名的值  也就是Id值
                int id = element.getAnnotation(XyBindView.class).value();
                // 添加指定的代码 并且添加分号和换行
                methodBuilder.addStatement("target."+name + " = ("+typeMirror+")"+"target.findViewById("+id+")");

            }
            //最后得到methodSpec 并加入到 TypeSpec， 可以理解成给一个类加一个方法
            MethodSpec methodSpec = methodBuilder.build();
            TypeSpec typeSpec = typeBuilder.addMethod(methodSpec).build();
            // 使用JavaPoet 动态创建出对应的类文件
            JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                    .build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return false;
    }
}
