package com.joker.demo.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import androidx.annotation.Nullable;

//容器
public class ContainerDemo {
    public static void main(String[] args) {
//         arrayListToArray();
//         hashset();
//         arrsort();
//         treesetSort();
         hashcode();

    }

    private static void hashcode() {
        //Java中自带的数据类型
        System.out.println("先测试Java中自带的数据类型:");
        HashMap<String, Double> hashMap1 = new HashMap<String,Double>();
        hashMap1.put("zhangsan", 96.0);
        hashMap1.put("lisi", 88.6);
        hashMap1.put("wangwu", 98.6);
        hashMap1.put("wangting",  87.5);
        hashMap1.put("zhangsan", 96.0);
        hashMap1.put("lisi", 88.6);
        hashMap1.put("wangwu", 98.6);
        hashMap1.put("wangting",  87.5);

        Set<String> keySet = hashMap1.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext())
        {
            String key = iterator.next();
            System.out.println(key+"\t"+hashMap1.get(key));
        }
        System.out.println("Java中自带的数据类型:相同的对象会覆盖！");
        System.out.println("\n");
        //用户自定义的数据类型：为重写之前
        System.out.println("测试用户自定义的数据类型--未重写两个方法之前:");
        HashMap<Student, String> hashMap2 = new HashMap<Student,String>();
        hashMap2.put(new Student(88,"zhangsan"), "beijing");
        hashMap2.put(new Student(88,"lisi"), "beijing");
        hashMap2.put(new Student(66,"wangwu"), "beijing");
        hashMap2.put(new Student(88,"zhangsan"), "beijing");
        hashMap2.put(new Student(88,"lisi"), "beijing");
        hashMap2.put(new Student(66,"wangwu"), "beijing");
        Set<Student> keySet2 = hashMap2.keySet();
        Iterator<Student> iterator2 = keySet2.iterator();
        while(iterator2.hasNext())
        {
            Student key = iterator2.next();
            System.out.println(key+"\t"+hashMap2.get(key));
        }
        System.out.println("如果没有重写hashcode和equals:导致相同的对象不会被覆盖!");



    }

    //因为id相等，认为两个student相同，故不显示第2个
    private static void treesetSort() {
        TreeSet<Student> arr = new TreeSet<Student>();
        arr.add(new Student(89,"zhangsan"));
        arr.add(new Student(90,"lisi"));
        arr.add(new Student(60,"wangwu"));
        arr.add(new Student(85,"qangting"));
        arr.add(new Student(85,"dangting"));

        for (Student student : arr)
        {
            System.out.println(student);
        }
    }

    private static void arrsort() {
        ArrayList<Student> arr = new ArrayList<Student>();
        arr.add(new Student(89,"zhangsan"));
        arr.add(new Student(90,"lisi"));
        arr.add(new Student(60,"wangwu"));
        arr.add(new Student(85,"wangting"));

        Collections.sort(arr);

        for (Student student : arr)
        {
            System.out.println(student);
        }
    }

    private static void hashset() {
        HashSet<Integer> hashSet1=new HashSet<>();
        hashSet1.add(100);
        hashSet1.add(200);
        hashSet1.add(200);
        hashSet1.add(400);
        hashSet1.add(10);
        hashSet1.add(20);
        hashSet1.add(8000);
        hashSet1.add(1000);
        hashSet1.add(2000);
        hashSet1.add(2000);
        hashSet1.add(4000);
        hashSet1.add(100);
        hashSet1.add(200);
        hashSet1.add(8000);
        for(Integer integer:hashSet1){
            System.out.println(integer);
        }
        System.out.println("--------------------");
        HashSet<Student> hashSet2 = new HashSet<Student>();     //此时Student有一个问题,没有实现equals方法和hashCode方法.
        hashSet2.add(new Student(1000, "wtt"));
        hashSet2.add(new Student(2000, "wtt"));
        hashSet2.add(new Student(3000, "wtt"));
        hashSet2.add(new Student(3000, "wtt"));
        hashSet2.add(new Student(100, "wtt"));
        hashSet2.add(new Student(50, "wtt"));
        hashSet2.add(new Student(10, "wtt"));
        for (Student student : hashSet2)
        {
            System.out.println(student);          //尽管Student类实现了compareTo方法,也是没用的,每次打印出元素的顺序是不一样的.
        }

        System.out.println("--------------------");
    }

    static void arrayListToArray(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(12);
        arrayList.add(10);
        arrayList.add(35);
        arrayList.add(100);

        Iterator iterator = arrayList.iterator();//获取容器的迭代器
        while (iterator.hasNext()) {
            Integer value = (Integer) iterator.next();//获取当前游标右边的元素，同时游标右移-->
            System.out.println(value);
        }
        System.out.println("通过ArrayList容器获取一个数组arr：");
        Object[] arr= arrayList.toArray();
        for(int i=0;i<arr.length;i++)
        {
            System.out.println(arr[i]);
        }
    }

    static class Student implements Comparable<Object>{

        int id;
        String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            Student student= (Student) o;
            if(this.id>student.id){
                return 1;
            }else if(this.id==student.id){
                return 0;
            }
            return -1;
        }


        @Override
        public int hashCode() {
            return this.name.hashCode()*id;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            Student stu= (Student) obj;
            System.out.println("地址："+this.name.hashCode());
            System.out.println("地址2："+stu.name.hashCode());
            return this.name==stu.name&&this.id==stu.id;
        }
    }
}
