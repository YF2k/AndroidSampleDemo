package com.joker.demo.javase;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakRefDemo {

    public static void main(String[] args) throws InterruptedException {

        test1();
        test2();
    }

    private static void test2() throws InterruptedException {
        ReferenceQueue<BigObject> queue=new ReferenceQueue<>();
        BigObject bigObject=new BigObject();

        WeakReference<BigObject> reference=new WeakReference<>(bigObject,queue);
        System.out.println("before gc, reference.get is "+reference.get());
        System.out.println("before gc, queue is "+queue.poll());
        System.gc();
        Thread.sleep(1000);
        System.out.println("after gc, reference.get is "+reference.get());
        System.out.println("after gc, queue is "+queue.poll());
    }

    private static void test1() throws InterruptedException {
        WeakReference<BigObject> reference=new WeakReference<>(new BigObject());
        System.out.println("before gc, reference.get is "+reference.get());
        System.gc();
        Thread.sleep(1000);
        System.out.println("after gc, reference.get is "+reference.get());
    }


    static class BigObject{

    }
}
