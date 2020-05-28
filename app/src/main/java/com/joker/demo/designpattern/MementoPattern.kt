package com.joker.demo.designpattern

//备忘录模式--在不破坏封闭的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样，以后就可将该对象恢复到原先保存的状态
//场景：如果用一个接口来让其他对象得到这些状态，将会暴露对象的实现细节并破坏对象的封装性，一个对象不希望外界直接访问其内部状态，通过中间对象可以间接访问其内部状态。

fun main(){

    // 大汉帝国
    val han =  Han();
    // 汉武帝建元年间 司马到长安任太史令
    System.out.println("汉武帝建元年间,司马到长安任太史令。");
    val sm =  SiMaQian();
    // 公元前108年
    han.setThing("天下大旱。");
    val h =  History(han.getThing());
    sm.addHistoryList("公元前108年", h);
    // 公元前104年
    han.setThing("汉朝历法改革，制定<<汉历>>。");
    val h1 =  History(han.getThing());
    sm.addHistoryList("公元前104年", h1);
    // 公元前99年
    han.setThing("李陵率步兵五千涉单于庭以寡击众，粮尽矢绝后，被迫投降。");
    val h2 =  History(han.getThing());
    sm.addHistoryList("公元前99年", h2);

    System.out.println("公元前108年:" + sm.getHistoryList("公元前108年")?.getThing());
    System.out.println("公元前104年:" + sm.getHistoryList("公元前104年")?.getThing());
    System.out.println("公元前99年:" + sm.getHistoryList("公元前99年")?.getThing());
    // 历史不可倒退 故无法回到过去
}

//原发器---西汉王朝
class Han{
    //事件
    private var thing=""

    fun getThing(): String {
        return thing
    }

    fun setThing(thing:String){
        this.thing=thing
    }

}

//备忘录角色
class History(thing: String){
    private var thing=""

    init {
        this.thing=thing
    }

        fun getThing(): String {
            return thing;
        }

}

//负责任角色---司马迁
class SiMaQian{
    var historyMap=HashMap<String,History>()

    fun addHistoryList(year:String,history: History){
        historyMap.put(year,history)
    }

    fun getHistoryList(year: String): History? {
        if(historyMap.containsKey(year)){
            return historyMap.get(year)
        }
        return null
    }
}

