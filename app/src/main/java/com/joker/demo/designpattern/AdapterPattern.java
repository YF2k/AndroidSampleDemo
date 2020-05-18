package com.joker.demo.designpattern;

//适配器模式
public class AdapterPattern {
    public static void main(String[] args){

        VoltAdapter voltAdapter=new VoltAdapter();
        System.out.println("voltAdapter:"+voltAdapter.getVolt5());

        VoltAdapter2 voltAdapter2=new VoltAdapter2(new Volt220());
        System.out.println("voltAdapter:"+voltAdapter2.getVolt5());
    }


    //目标接口
    interface FiveVolt{
        int getVolt5();
    }

    //源，需要被转换的对象
    static class Volt220{
        private int output=220;
        public int getVolt220(){
            System.out.println("电源输出电压：" + output);
            return output;
        }
    }

    //类适配器
    //适配器，将220--》5
    static class VoltAdapter extends Volt220 implements FiveVolt{

        @Override
        public int getVolt5() {
            int output=getVolt220();
            System.out.println("电源适配器开始工作，此时输出电压是：" + output);
            output = output/44;
            System.out.println("电源适配器工作完成，此时输出电压是：" + output);
            return output;
        }
    }
    //----------------------------------
    //对象适配器
    static class VoltAdapter2  implements FiveVolt{
        Volt220 mVolt220;

        public VoltAdapter2(Volt220 mVolt220) {
            this.mVolt220 = mVolt220;
        }

        @Override
        public int getVolt5() {
            int output=mVolt220.getVolt220();
            System.out.println("电源适配器开始工作，此时输出电压是：" + output);
            output = output/44;
            System.out.println("电源适配器工作完成，此时输出电压是：" + output);
            return output;
        }
    }
}
