package com.joker.demo.javase;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteTest {
    static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    static String number="0";

    public static void main(String[] args) {
        Thread t1 = new Thread(new Reader(),"读线程 1");
        Thread t2 = new Thread(new Reader(),"读线程 2");
        Thread t3 = new Thread(new Writer(),"写线程 1");

        t1.start();
        t2.start();
        t3.start();

    }

    static class Reader implements Runnable {

        @Override
        public void run() {

            ReentrantReadWriteLock.ReadLock readLock= readWriteLock.readLock();
            for(int i=0;i<=10;i++){
                readLock.lock();
                System.out.println(Thread.currentThread().getName()+"---Number is "+number);
                readLock.unlock();
                try {
//                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        }
    }

    static class Writer implements Runnable {


        @Override
        public void run() {
            ReentrantReadWriteLock.WriteLock writeLock= readWriteLock.writeLock();
            for(int i=1;i<=7;i+=2){
                try {
                    writeLock.lock();
                    System.out.println(Thread.currentThread().getName()+"正在写入"+i);
                    number=number.concat(""+i);
                    try {
//                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }finally {
                    writeLock.unlock();
                }

            }
        }
    }
}
