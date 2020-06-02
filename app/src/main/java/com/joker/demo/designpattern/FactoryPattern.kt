package com.joker.demo.designpattern

//工厂模式---定义一个用于创建对象的接口，让子类决定实例化哪个类
//在任何需要生成复杂对象的地方，都可以使用工厂方法模式。复杂对象适合使用工厂模式，用 new 就可以完成创建的对象无需使用 Factory
fun main(){
    var a=99
    var b=88
    var addOperationFactoryImpl=AddOperationFactoryImpl()
    var addOperationImpl= addOperationFactoryImpl.createOperation()
    var result=addOperationImpl.getResult(a,b)
    println(result)

    var subOperationFactoryImpl=SubOperationFactoryImpl()
    var subOperationImpl1= subOperationFactoryImpl.createOperation()
    result=subOperationImpl1.getResult(a,b)
    println(result)

    var mulOperationFactoryImpl=MulOperationFactoryImpl()
    var mulOperationImpl= mulOperationFactoryImpl.createOperation()
     result=mulOperationImpl.getResult(a,b)
    println(result)


}

//抽象工厂
interface IOperationFactory{
    fun createOperation():IOperation1
}

//具体工厂
class AddOperationFactoryImpl:IOperationFactory{
    override fun createOperation(): IOperation1 {
        return AddOperationImpl1()
    }

}

class SubOperationFactoryImpl:IOperationFactory{
    override fun createOperation(): IOperation1 {
        return SubOperationImpl1()
    }

}

class MulOperationFactoryImpl:IOperationFactory{
    override fun createOperation(): IOperation1 {
        return MulOperationImpl1()
    }

}

//抽象产品
interface IOperation1{
    fun getResult(a:Int,b:Int):Int
}

//具体产品
class AddOperationImpl1 :IOperation1{
    override fun getResult(a: Int, b: Int): Int {
        return a+b
    }

}

class SubOperationImpl1 :IOperation1{
    override fun getResult(a: Int, b: Int): Int {
        return a-b
    }

}

class MulOperationImpl1 :IOperation1{
    override fun getResult(a: Int, b: Int): Int {
        return a*b
    }

}