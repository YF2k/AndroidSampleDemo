package com.joker.demo.designpattern;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

//享元模式（Flyweight），运用共享技术有效地支持大量细粒度的对象。
//场景：我们在需要创建大量的相似的对象时，使用享元模式
public class FlyweightPattern {
    public static void main(String[] args){
        Ticket ticket1 = TicketFactory.getTicket("北京", "上海");
        ticket1.showTicketInfo("上铺");
        Ticket ticket7 = TicketFactory.getTicket("北京", "上海");
        ticket7.showTicketInfo("下铺");
        Ticket ticket2 = TicketFactory.getTicket("北京", "上海");
        ticket2.showTicketInfo("上铺");
        Ticket ticket3 = TicketFactory.getTicket("北京", "上海");
        ticket3.showTicketInfo("上铺");
        Ticket ticket4 = TicketFactory.getTicket("北京", "成都");
        ticket4.showTicketInfo("下铺");
        Ticket ticket5 = TicketFactory.getTicket("北京", "上海");
        ticket5.showTicketInfo("上铺");
        Ticket ticket6 = TicketFactory.getTicket("北京", "上海");
        ticket6.showTicketInfo("上铺");


    }

    //展示车票信息接口
    public interface Ticket {
        public void showTicketInfo(String info);
    }

    //展示车票具体实现:
    public static class TrainTicket implements Ticket {
        public String from;
        public String to;
        public String bunk;
        public int price;

        public TrainTicket(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void showTicketInfo(String info) {
            bunk = info;
            price = new Random().nextInt(300);
            System.out.println("购买从" + from + " -> " + to + "的 " + bunk + " 火车票 ，价格：" + price);

        }
    }

    public static class TicketFactory {

        static Map<String, Ticket> sTicketMap = new ConcurrentHashMap<>();

        public static Ticket getTicket(String from, String to) {
            String key = from + "-" + to;
            if (sTicketMap.containsKey(key)) {
                //使用已经存在的对象
                System.out.println("使用存在的对象 = [" + from + "], to = [" + to + "]");
                return sTicketMap.get(key);
            } else {
                System.out.println("创建对象 = [" + from + "], to = [" + to + "]");
                TrainTicket trainTicket = new TrainTicket(from, to);
                sTicketMap.put(key, trainTicket);
                return trainTicket;
            }

        }
    }





    public static void test(){
        String a = "mingmingcome";
        String b = "mingmingcome";


        System.out.println(a == b); // true
        System.out.println(a); // mingmingcome
        String c = new String("mingmingcome");
        String d = new String("mingmingcome");

        System.out.println(c.equals(d));
        // 利用反射修改String中私有成员变量char[]
        Field value = null;
        try {
            value = c.getClass().getDeclaredField("value");
            value.setAccessible(true);
            char[] o = (char[])value.get(c);
            o[0] = 'L';
            o[1] = 'O';
            o[2] = 'V';
            o[3] = 'E';
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(a); //LOVEmingcome
        System.out.println(b); //LOVEmingcome
        System.out.println(c); //LOVEmingcome
        System.out.println(d); //LOVEmingcome
    }

}
