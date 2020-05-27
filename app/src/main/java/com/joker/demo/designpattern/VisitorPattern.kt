package com.joker.demo.designpattern

import java.util.*
//访问者模式：封装一些作用于某种数据结构中的各元素的操作，它可以在不改变这个数据结构的前提下定义作用于这些元素的新的操作
//使用场景：1、对象结构比较稳定，但经常需要在此对象进行很多不同的并且不相关的操作。
//2、需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免这些操作 “污染” 这些对象的类，也不希望在增加新操作时修改这些类。
fun main(){
    //构建报表
    val report = BusinessReport()
    println("=========== CEO看报表 ===========")
    report.showReport(CEOVisitor())
    println("=========== CTO看报表 ===========")
    report.showReport(CTOVisitor())
}


open abstract class Staff(var name:String,var kpi:Int){


// 核心方法，接受Visitor的访问
    abstract fun accept(visitor: Visitor)
}

//工程师
class Engineer(name:String,kpi:Int) :Staff(name,kpi){
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    //工程师一年的代码数量
    fun getCodeLines(): Int {
        val lines=(1000..10000).random();
        return lines
    }

}

//经理
class Manager(name:String,kpi:Int) :Staff(name,kpi){
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    // 一年做的产品数量
    fun getProducts(): Int {
        val num=(1..10).random();
        return num
    }

}


interface Visitor{
    //访问工程师类型
    fun visit(engineer: Engineer)
    //访问经理类型
    fun visit(manager: Manager)
}

class CEOVisitor :Visitor{
    override fun visit(engineer: Engineer) {
        println("工程师: " + engineer.name + ", KPI: " + engineer.kpi)
    }

    override fun visit(manager: Manager) {
        println("经理: " + manager.name + ", KPI: " + manager.kpi +
                ", 新产品数量: " + manager.getProducts())
    }

}

class CTOVisitor :Visitor{
    override fun visit(engineer: Engineer) {
        println("工程师: " + engineer.name + ", 代码行数: " + engineer.getCodeLines())
    }

    override fun visit(manager: Manager) {
        println("经理: " + manager.name + ", 产品数量: " + manager.getProducts())
    }

}


//员工业绩报表
class BusinessReport{
    var mStaffs=LinkedList<Staff>()

    init{
        mStaffs.add(Manager("经理A",1))
        mStaffs.add(Engineer("工程师A",3))
        mStaffs.add(Engineer("工程师B",4))
        mStaffs.add(Engineer("工程师C",5))
        mStaffs.add(Manager("经理B",8))
        mStaffs.add(Engineer("工程师D",9))
    }

    //为访问者展示报表
    fun showReport(visitor: Visitor){
        for(staff in mStaffs){
            staff.accept(visitor)
        }
    }
}