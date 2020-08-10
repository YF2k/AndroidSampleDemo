package com.joker.demo.memoryoptimization;
public class Outer {

    private int count;

    //匿名内部类1
    private StaticInner si1 = new StaticInner(){
        @Override
        public void doAction() {
            count++;

        }
    };

    private StaticInner si2;

    private Inner i3;


    public void setInner(StaticInner inner) {
        this.si2 = inner;
    }


    public Outer() {
        i3 = new Inner();
        //匿名内部类2
        setInner(new StaticInner(){
            @Override
            public void doAction() {
                super.doAction();
                count++;
            }
        });
    }

    public void doAction() {
        si1.doAction();
        si2.doAction();
        i3.doSomething();
    }


    /**
     * 内部类
     */
    private class Inner {

        public void doSomething() {
            count++;
        }
    }


    /**
     * 静态内部类
     */
    private static class StaticInner{

        public void doAction() {

        }
    }
}
