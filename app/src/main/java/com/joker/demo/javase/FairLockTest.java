package com.joker.demo.javase;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest implements Runnable{
    int sharedNumber=0;

    //创建公平锁
     static ReentrantLock reentrantLock=new ReentrantLock(true);


    @Override
    public void run() {
        while(sharedNumber<20){
            try{
                reentrantLock.lock();
                sharedNumber++;
                System.out.println(Thread.currentThread().getName()+"获得锁，sharedNumber is "+sharedNumber);
            }finally {
                reentrantLock.unlock();
            }

        }
    }


    public static void main(String[] args){
        FairLockTest fairLockTest=new FairLockTest();
        Thread t1=new Thread(fairLockTest);
        Thread t2=new Thread(fairLockTest);
        Thread t3=new Thread(fairLockTest);
        t1.start();
        t2.start();
        t3.start();
    }


}
