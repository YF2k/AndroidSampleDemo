package com.joker.demo.javase;

import java.util.concurrent.locks.ReentrantLock;


//你可能已经注意到，在上面 ReentrantLock 的使用中，我将 unlock 操作放在 finally 代码块中。这是因为 ReentrantLock 与 synchronized 不同，当异常发生时 synchronized 会自动释放锁，但是 ReentrantLock 并不会自动释放锁。因此好的方式是将 unlock 操作放在 finally 代码块中，保证任何时候锁都能够被正常释放掉

//默认情况下，synchronized 和 ReentrantLock 都是非公平锁。但是 ReentrantLock 可以通过传入 true 来创建一个公平锁。所谓公平锁就是通过同步队列来实现多个线程按照申请锁的顺序获取锁
public class ReentrantLockTest {
    ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) {


        ReentrantLockTest st1 = new ReentrantLockTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                st1.printLog();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                st1.printLog();
            }
        });

        t1.start();
        t2.start();
    }


    public void printLog() {
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
