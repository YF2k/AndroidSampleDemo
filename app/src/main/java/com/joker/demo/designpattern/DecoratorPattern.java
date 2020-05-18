package com.joker.demo.designpattern;

//装饰模式
//动态地给一个对象添加一些额外的职责，就增加功能来说，装饰者模式比生成子类更为灵活。——《设计模式：可复用面向对象软件的基础》
//装饰者模式是一种对象结构型模式。
public class DecoratorPattern {
     public static void main(String[] args){
         Person person=new Boy();
         //先穿上便宜的衣服
         PersonCloth cheapCloth=new CheapCloth(person);
         cheapCloth.dressed();
         System.out.println("========================");
         //或者在穿上有点档次的衣服
         PersonCloth personCloth = new ExpensiveCloth(person);
         personCloth.dressed();


     }

     abstract static interface Person{
         //穿着-抽象方法
          void dressed();
     }

     static class Boy implements Person{

         @Override
         public void dressed() {
             System.out.println("男孩穿着内裤");
         }
     }

     //装饰的抽象类
    static abstract class PersonCloth implements Person{
         Person person;

         public PersonCloth(Person person) {
             this.person = person;
         }

         @Override
         public void dressed() {
             person.dressed();
         }
     }
     
     //具体的装饰
     static class ExpensiveCloth extends PersonCloth{


         public ExpensiveCloth(Person person) {
             super(person);
         }

         @Override
         public void dressed() {
             super.dressed();
             //穿短袖
             dressShirt();
             //穿皮衣
             dressLeather();
             //穿牛仔裤
             dressJean();
         }

         private void dressJean() {
             System.out.println("穿上短袖");
         }

         private void dressLeather() {
             System.out.println("穿上皮衣");
         }

         private void dressShirt() {
             System.out.println("穿上牛仔裤");
         }
     }

    public static class CheapCloth extends PersonCloth {
        public CheapCloth(Person person) {
            super(person);
        }

        @Override
        public void dressed() {
            super.dressed();
            dressShorts();
        }

        private void dressShorts() {
            System.out.println("穿条短裤");
        }
    }


}
