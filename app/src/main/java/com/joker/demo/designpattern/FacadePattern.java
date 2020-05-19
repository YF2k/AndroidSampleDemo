package com.joker.demo.designpattern;

//外观模式
//要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供一个高层次的接口，使得子系统更易于使用。
public class FacadePattern {
    public static void main(String[] args){
        ComputerFacade computerFacade=new ComputerFacade();
        computerFacade.start();
    }

    //CPU:
    static class  CPU{
        void freeze(){
            System.out.println("CPU执行freeze操作");
        }

        void jump(){
            System.out.println("CPU执行jump操作");
        }

        void execute(){
            System.out.println("CPU正常运行");
        }
    }

    //硬盘：
    public static class HardDrive {
        public void read() {
            System.out.println("读取硬盘");
        }
    }

    //内存
    public static class Memory {
        public void load() {
            System.out.println("将硬盘中读取到信息加载到运行内存");
        }
    }

    //外观类：
    public static class ComputerFacade {
        private CPU processor;
        private Memory ram;
        private HardDrive hd;

        public ComputerFacade() {
            this.processor = new CPU();
            this.ram = new Memory();
            this.hd = new HardDrive();
        }

        public void start() {
            processor.freeze();
            hd.read();
            ram.load();
            processor.jump();
            processor.execute();
            System.out.println("计算机正常启动完毕");
        }
    }
}
