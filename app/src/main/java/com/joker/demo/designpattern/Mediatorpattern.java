package com.joker.demo.designpattern;

import java.util.HashMap;

//用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地互相引用，从而使其耦合松散，而且可以独立地改变它们的交互
public class Mediatorpattern {
    public static void main(String[] args){
        PostOffice postOffice=new PostOfficeImpl();
        She she=new She("村西边",postOffice);
        You you=new You("村东边",postOffice);

        postOffice.addPeople(she);
        postOffice.addPeople(you);

        you.sendLetter("正月十五，元宵之夜，一见倾心", "She");
        she.sendLetter("对不起，我已经有男朋友了", "You");
    }

    //抽象中介者
    interface PostOffice{
        //送信
        void deliverLetters(String letters,String receiver);
        //添加收信人
        void addPeople(Villager villager);
    }

    //具体的中介者模式
    static class PostOfficeImpl implements PostOffice{
        HashMap<String,Villager> villagerHashMap=new HashMap<>();

        @Override
        public void deliverLetters(String letters,String receiver) {
            System.out.println("=>收信：邮局收到要寄的信");
            Villager villager = (Villager) villagerHashMap.get(receiver);
            System.out.println("=>送信：拿出地址本查询收信人地址是：" + villager.getAddress() + "，送信");
            System.out.println("=>收信人看信：");
            villager.receiveLetter(letters);
        }

        @Override
        public void addPeople(Villager villager) {
            villagerHashMap.put(villager.getClass().getSimpleName(), villager);
        }
    }

    //抽象人
    static abstract class Villager{
        String address;
        PostOffice postOffice;

        public Villager(String address,PostOffice postOffice) {
            this.address = address;
            this.postOffice=postOffice;
        }

        //收信
        void receiveLetter(String letter){
            System.out.println(letter);
        }

        //送信
        void sendLetter(String letter,String receiver){
            postOffice.deliverLetters(letter,receiver);
        }

        String getAddress(){
            return address;
        }
    }

    //她
    static class She extends Villager{
        public She(String address, PostOffice postOffice) {
            super(address, postOffice);
        }
    }

    //你
    static class You extends Villager {
        public You(String address, PostOffice postOffice) {
            super(address, postOffice);
        }
    }


}
