package com.joker.demo.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//反射
public class ReflectDemo {

    public static void main(String[] args){
            try {
                //成员变量
                Class<Student> aClass = (Class<Student>) Class.forName("com.joker.demo.reflect.Student");
                //获取字段
                //  1.1 获取所有字段 -- 字段数组
                //     可以获取公用和私有的所有字段，但不能获取父类字段
                Field[] declaredFields=aClass.getDeclaredFields();
                for (Field field:declaredFields) {
                    System.out.println(field);
                }
                System.out.println("=============================");
                //获取指定字段
                Field field=aClass.getDeclaredField("name");
                System.out.println(field+":::"+field.getName());
                System.out.println("=============================");
                //获取方法
                // 获取clazz对应类中的所有方法--方法数组（一）
                // 不能获取private方法,并且获取从父类继承来的所有方法
                Method[] methods=aClass.getMethods();
                    for(Method method:methods){
                        System.out.println(method);
                    }
                System.out.println("================================");
                //2.获取方法
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method:declaredMethods) {
                    System.out.println(method);
                }
                System.out.println("================================");
                //获取具体方法
                Method method=aClass.getDeclaredMethod("getScore",String.class);
                System.out.println(method);
                System.out.println("================================");
                //获取注解
                Annotation[] annotations= aClass.getDeclaredAnnotations();
                for(Annotation annotation:annotations){
                    System.out.println(annotation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
