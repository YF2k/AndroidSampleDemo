package com.joker.demo.designpattern;

//桥接模式   将抽象部分与实现部分分离，使它们都可以独立地进行变化
//使用场景：从模式的定义中我们大致可以了解到，这里的 ”桥梁“ 的作用其实就是连接 ”抽象部分“ 与 "实现部分"，但是事实上，任何多维度变化类或者说多个树状类之间的耦合都可以使用桥接模式来实现解耦。
public class BridgePattern {
    public static void main(String[] args){
// 手工蛋糕订单
        OrderAbstraction handworkCake =
                new CakeOrder(10,
                        new HandworkFactoryImp());
        // 机器蛋糕订单
        OrderAbstraction machineCake =
                new CakeOrder(10,
                        new MachineFactoryImp());
        // 手工糖果订单
        OrderAbstraction handworkCandy =
                new CandyOrderRefinedAbstraction(10,
                        new HandworkFactoryImp());
        // 机器糖果订单
        OrderAbstraction machineCandy =
                new CandyOrderRefinedAbstraction(10,
                        new MachineFactoryImp());

        handworkCake.provide();
        machineCake.provide();
        handworkCandy.provide();
        machineCandy.provide();
    }

    //实现化角色
    interface FactoryImplementor{
        void provide(int count,String orderName);
    }

    //具体实现化角色
    //手工作坊
    static class HandworkFactoryImp implements FactoryImplementor{

        @Override
        public void provide(int count, String orderName) {
            float time = (float)(count * 1);
            System.out.println("手工使用了" + time + "小时，完成了" + count + "份" + orderName);
        }
    }

    // 工厂机器
    public static class MachineFactoryImp implements FactoryImplementor {

        public void provide(int count, String orderName) {
            float time = (float)(count * 0.5);
            System.out.println("机器使用了" + time + "小时，完成了" + count + "份" + orderName);
        }

    }


    //抽象化角色
    //订单
    abstract static class OrderAbstraction{
        FactoryImplementor factoryImplementor;

        public OrderAbstraction(FactoryImplementor factoryImplementor) {
            this.factoryImplementor = factoryImplementor;
        }

        abstract void provide();
    }

    //扩充抽象化角色
    // 蛋糕订单
    static class CakeOrder extends OrderAbstraction{
        int count;
        String orderName="蛋糕";

        public CakeOrder(int count,FactoryImplementor factoryImplementor) {
            super(factoryImplementor);
            this.count = count;
        }

        @Override
        void provide() {
            factoryImplementor.provide(count, orderName);
        }
    }

    // 糖果订单
    public static class CandyOrderRefinedAbstraction extends OrderAbstraction {
        private int count;
        private String orderName = "糖果";

        public CandyOrderRefinedAbstraction(int count, FactoryImplementor factoryImplementor) {
            super(factoryImplementor);
            this.count = count;
        }

        @Override
        public void provide() {
            factoryImplementor.provide(count, orderName);
        }

    }
}
