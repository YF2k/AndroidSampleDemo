package com.joker.demo.container;

import java.util.ArrayList;
import java.util.Iterator;

public class ContainerDemo {
    public static void main(String[] args) {
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
}
