package com.joker.demo.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//为其它对象提供一种代理以控制对这个对象的访问
//代理模式可以大致分为两大部分，一是静态代理，而是动态代理。静态代理是只有具体的代理对象，
// 而动态代理则与静态代理相反，通过反射机制动态地生成代理者的对象，也就是说我们在 code 阶段压根不需要知道代理对象是谁，代理谁将会在执行阶段决定。
public class ProxyPattren {
    public static void main(String[] args){
        //静态代理
        //程序员
        ILawsuit iLawsuit=new XProgrammer();
        //律师
        ILawsuit proxyLawyer=new ProxyLawyer(iLawsuit);
        //律师开始处理
        proxyLawyer.submit();
        proxyLawyer.burden();
        proxyLawyer.defend();
        proxyLawyer.finish();

        //动态代理
        //构造一个动态代理对象（程序员请的律师，把自己的事务交于律师来处理）
        DynamicProxy dynamicProxy = new DynamicProxy(iLawsuit);
        //拿到代理者身上的 ClassLoader
        ClassLoader classLoader = iLawsuit.getClass().getClassLoader();
        ILawsuit proxyLawyer2 = (ILawsuit) Proxy.newProxyInstance(classLoader,new Class[]{ILawsuit.class},dynamicProxy);
//律师开始处理
        proxyLawyer2.submit();
        proxyLawyer2.burden();
        proxyLawyer2.defend();
        proxyLawyer2.finish();


    }

    //诉讼接口
    public interface ILawsuit{
        /**
         * 提交申请
         */
        void submit();

        /**
         * 举行举证
         */
        void burden();

        /**
         * 开始辩护
         */
        void defend();

        /**
         * 诉讼完成
         */
        void finish();

    }

    //具体诉讼人
    static class XProgrammer implements ILawsuit{

        @Override
        public void submit() {
            System.out.println("老板欠 X 程序员工资，申请仲裁!");
        }

        @Override
        public void burden() {
            System.out.println("这是合同书和过去一年的银行工资流水");
        }

        @Override
        public void defend() {
            System.out.println("证据确凿！不需要再说什么了！");
        }

        @Override
        public void finish() {
            System.out.println("诉讼成功！判决老板即日起 7 天内结算工资！");
        }
    }

    //律师，代理对象
    static class ProxyLawyer implements ILawsuit{
        /**
         * 持有一个具体被代理者的引用，这里就是 X 程序员，也可以是其它 Y 程序员 只是具体说明。
         */
        private ILawsuit mLawsuit;

        public ProxyLawyer(ILawsuit mLawsuit) {
            this.mLawsuit = mLawsuit;
        }

        @Override
        public void submit() {
            mLawsuit.submit();
        }

        @Override
        public void burden() {
            mLawsuit.burden();
        }

        @Override
        public void defend() {
            mLawsuit.defend();
        }

        @Override
        public void finish() {
            mLawsuit.finish();
        }
    }


    //动态代理
    static class DynamicProxy implements InvocationHandler {
        /**
         * 代理者的引用（这里可以理解为 X 程序员）
         */
        private Object object;

        public DynamicProxy(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //通过反射调用其代理者的方法
            return method.invoke(object,args);
        }
    }
}
