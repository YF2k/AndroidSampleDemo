package com.joker.demo.javase.thread;

public class ThreadDemo {

    public static void main(String[] args) {
//        testThread();
//        testRunnable();
//        testjoin();
//        testInterrupt();
//        testDaemonThread();//后台线程
//        testPriority();//线程优先级
//        testYield();//线程的礼让
//        testSychronized();//线程的同步
        //死锁：同步可以保证资源共享操作的正确性，但是过多同步也会产生问题。例如，现在张三想要李四的画，李四想要张三的书，张三对李四说“把你的画给我，我就给你书”，李四也对张三说“把你的书给我，我就给你画”两个人互相等对方先行动，就这么干等没有结果，这实际上就是死锁的概念。
        //所谓死锁，就是两个线程都在等待对方先完成，造成程序的停滞，一般程序的死锁都是在程序运行时出现的。
//        testDeadLock();
        testWait();
        testWait2();
    }


    private static void testWait() {
        ThreadWait t1 = new ThreadWait("t-wait");
        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();
                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName()+" wait()");
                t1.wait();  //  不是使t1线程等待，而是当前执行wait的线程等待
                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadWait extends Thread{
        public ThreadWait(String name) {
            super(name);
        }
        public void run() {
            synchronized (this) {
                try {
                    Thread.sleep(1000);	//	使当前线阻塞 1 s，确保主程序的 t1.wait(); 执行之后再执行 notify()
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" call notify()");
                // 唤醒当前的wait线程
                this.notify();
            }
        }
    }
//------------

    private static void testWait2() {
        ThreadWait2 t1 = new ThreadWait2("t-wait2");
        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();
                // 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
                System.out.println(Thread.currentThread().getName() + " call wait ");
                t1.wait(3000);
                System.out.println(Thread.currentThread().getName() + " continue");
                t1.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadWait2 extends Thread{
        public ThreadWait2(String name) {
            super(name);
        }
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run ");
            // 死循环，不断运行。
            while(true){;}	//	这个线程与主线程无关，无 synchronized
        }
    }

    private static void testDeadLock() {
        ThreadDeadLock t1 = new ThreadDeadLock() ;      // 控制张三
        ThreadDeadLock t2 = new ThreadDeadLock() ;      // 控制李四
        t1.flag = true ;
        t2.flag = false ;
        Thread thA = new Thread(t1) ;
        Thread thB = new Thread(t2) ;
        thA.start() ;
        thB.start() ;
    }

    public static class ThreadDeadLock implements Runnable {
        private static Zhangsan zs = new Zhangsan();       // 实例化static型对象
        private static Lisi ls = new Lisi();       // 实例化static型对象
        private boolean flag = false;  // 声明标志位，判断那个先说话

        public void run() {  // 覆写run()方法
            if (flag) {
                synchronized (zs) {   // 同步张三
                    zs.say();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (ls) {
                        zs.get();
                    }
                }
            } else {
                synchronized (ls) {
                    ls.say();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (zs) {
                        ls.get();
                    }
                }
            }
        }
    }

    static class Zhangsan{ // 定义张三类
        public void say(){
            System.out.println("张三对李四说：“你给我画，我就把书给你。”") ;
        }
        public void get(){
            System.out.println("张三得到画了。") ;
        }
    }
   static class Lisi{ // 定义李四类
        public void say(){
            System.out.println("李四对张三说：“你给我书，我就把画给你”") ;
        }
        public void get(){
            System.out.println("李四得到书了。") ;
        }
    }

    private static void testSychronized() {
        Runnable mr = new Runnable() {
            private int ticket = 5;    // 假设一共有5张票

            private synchronized void saleMethod(){    // 声明同步方法
                if(ticket>0){   // 还有票
                    try{
                        Thread.sleep(300) ; // 加入延迟
                    }catch(InterruptedException e){
                        e.printStackTrace() ;
                    }
                    System.out.println("卖票：ticket = " + ticket-- );
                }

            }

            private void sale(){
                synchronized (this) { // 要对当前对象进行同步
                    if (ticket > 0) {   // 还有票
                        try {
                            Thread.sleep(300); // 加入延迟
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread:" + Thread.currentThread().getName() + ":卖票：ticket = " + ticket--);
                    } else {
                        System.out.println("Thread:" + Thread.currentThread().getName() + ":票卖光了");
                    }
                }
            }

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
//                    sale();
                    saleMethod();
                }
            }
        };
        Thread t1 = new Thread(mr);    // 定义Thread对象
        Thread t2 = new Thread(mr);    // 定义Thread对象
        Thread t3 = new Thread(mr);    // 定义Thread对象
        t1.start();
        t2.start();
        t3.start();
    }


    //在线程操作中，也可以使用 yield() 方法将一个线程的操作暂时让给其他线程执行
    private static void testYield() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                    System.out.println(Thread.currentThread().getName()
                            + "运行，i = " + i);  // 取得当前线程的名字
                    if (i == 2) {
                        System.out.print("线程礼让：");
                        Thread.currentThread().yield();    // 线程礼让
                    }
                }
            }
        }, "线程A");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                    System.out.println(Thread.currentThread().getName()
                            + "运行，i = " + i);  // 取得当前线程的名字
                    if (i == 2) {
                        System.out.print("线程礼让：");
                        Thread.currentThread().yield();    // 线程礼让
                    }
                }
            }
        }, "线程B");
        t1.start();
        t2.start();
    }

    //从程序的运行结果中可以观察到，线程将根据其优先级的大小来决定哪个线程会先运行，但是需要注意并非优先级越高就一定会先执行，哪个线程先执行将由 CPU 的调度决定。
    private static void testPriority() {
        Thread t1 = new Thread(new MyRunnable(), "线程A");  // 实例化线程对象
        Thread t2 = new Thread(new MyRunnable(), "线程B");  // 实例化线程对象
        Thread t3 = new Thread(new MyRunnable(), "线程C");  // 实例化线程对象
        t1.setPriority(Thread.MIN_PRIORITY);   // 优先级最低
        t2.setPriority(Thread.MAX_PRIORITY);   // 优先级最高
        t3.setPriority(Thread.NORM_PRIORITY);  // 优先级最中等
        t1.start();    // 启动线程
        t2.start();    // 启动线程
        t3.start();    // 启动线程
    }

    //https://blog.csdn.net/chengqiuming/article/details/90573992
    //所有的前台线程都死亡时，后台线程随之死亡
    private static void testDaemonThread() {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r, "后台线程");
        t.setDaemon(true);
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "  " + i);
        }
        // -----程序执行到此处，前台线程（main线程）结束------
        // 后台线程也应该随之结束
    }

    private static void testInterrupt() {
        MyRunnable2 mr = new MyRunnable2();  // 实例化Runnable子类对象
        Thread t = new Thread(mr, "线程");     // 实例化Thread对象
        t.start(); // 启动线程
        try {
            Thread.sleep(2000);    // 线程休眠2秒
        } catch (InterruptedException e) {
            System.out.println("3、休眠被终止");
        }
        t.interrupt(); // 中断线程执行
    }

    static class MyRunnable2 implements Runnable { // 实现Runnable接口
        public void run() {  // 覆写run()方法
            System.out.println("1、进入run()方法");
            try {
                Thread.sleep(10000);   // 线程休眠10秒
                System.out.println("2、已经完成了休眠");
            } catch (InterruptedException e) {
                System.out.println("3、休眠被终止");
                return; // 返回调用处
            }
            System.out.println("4、run()方法正常结束");
        }
    }

    ;

    //https://www.cnblogs.com/java1024/archive/2019/11/28/11950129.html
    private static void testjoin() {
        MyRunnable mr = new MyRunnable();  // 实例化Runnable子类对象
        Thread t = new Thread(mr, "线程");     // 实例化Thread对象
        t.start(); // 启动线程
        for (int i = 0; i < 50; i++) {
            if (i > 10) {
                try {
                    t.join();  // 线程强制运行
                } catch (Exception e) {
                }
            }
            System.out.println("Main线程运行 --> " + i);
        }

    }

    static class MyRunnable implements Runnable { // 实现Runnable接口
        public void run() {  // 覆写run()方法
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()
                        + "运行，i = " + i);  // 取得当前线程的名字
            }
        }
    }


    private static void testRunnable() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("RunnableThread:" + Thread.currentThread().getName());
            }
        };
        Thread t1 = new Thread(r, "线程A");
        t1.start();
    }

    private static void testThread() {
        Thread t1 = new MyThread();
        t1.start();

    }


    public static class MyThread extends Thread {

        public MyThread() {
            super("线程B");
        }

        @Override
        public void run() {
            super.run();
            //sout 迅速打出 System.out.println
            System.out.println("myThread:" + this.getName());
        }
    }


}
