package com.joker.demo.javase;

//synchronized 可以用来修饰以下 3 个层面：
//
//修饰实例方法:这种情况下的锁对象是当前实例对象，因此只有同一个实例对象调用此方法才会产生互斥效果，不同实例对象之间不会有互斥效果
//
//修饰静态类方法:如果 synchronized 修饰的是静态方法，则锁对象是当前类的 Class 对象。因此即使在不同线程中调用不同实例对象，也会有互斥效果
//
//修饰代码块。
public class SynchronizedTest {
    private Object lock=new Object();

    public static void main(String[] args) {
        SynchronizedTest st1 = new SynchronizedTest();
        SynchronizedTest st2 = new SynchronizedTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                st1.printLog();
//                st1.synPrintLog();
                st1.synStaticPrintLog();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                st2.printLog();
//                st1.printLog();
//                st1.synPrintLog();
//                st2.synPrintLog();
                st2.synStaticPrintLog();
            }
        });

        t1.start();
        t2.start();
    }


    public void printLog() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修饰实例方法
    //只有某一个线程中的代码执行完之后，才会调用另一个线程中的代码。也就是说此时两个线程间是互斥的
    public synchronized void synPrintLog() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //修饰静态类方法
    public static synchronized void synStaticPrintLog() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修饰代码块
    public   void synObjectPrintLog() {
        synchronized (lock){
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
            }
        }
    }

    //wait
    public void testWait(){
        System.out.println(Thread.currentThread().getName()+"start");
        //pthread_mutex_lock函数     pthread库
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+"excuted");
            try{
                //sleep()
                wait(5000);//不仅释放时间片，还释放锁资源
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"end");
    }

}
