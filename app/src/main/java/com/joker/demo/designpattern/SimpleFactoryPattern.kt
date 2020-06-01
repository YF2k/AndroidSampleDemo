package com.joker.demo.designpattern

//简单（静态）工厂模式---可以根据参数的不同返回不同类的实例
fun main(){
    var a=99
    var b=88
    //加
    var operationImpl= SimpleFactory.createOperation("+")
    println(operationImpl?.getResult(a,b))
    //减
     operationImpl= SimpleFactory.createOperation("-")
    println(operationImpl?.getResult(a,b))
    //乘
    operationImpl= SimpleFactory.createOperation("*")
    println(operationImpl?.getResult(a,b))


}

//抽象产品类
interface IOperation{
    fun getResult(a:Int,b:Int):Int
}

//具体产品类
//加法运算类
class AddOperationImpl : IOperation{
    override fun getResult(a:Int,b:Int):Int {
        return  a+b
    }

}

//减法运算类
class SubOperationImpl :IOperation{
    override fun getResult(a: Int, b: Int): Int {
        return a-b
    }

}


//乘法运算类
class MulOperationImpl :IOperation{
    override fun getResult(a: Int, b: Int): Int {
        return a*b
    }


}

//工厂
class SimpleFactory{
    //静态方法
    companion object{
        var operation: IOperation? =null
        fun createOperation(op:String):IOperation?{
            when(op){
                "+" -> operation=AddOperationImpl()
                "-" ->operation=SubOperationImpl()
                "*" ->operation=MulOperationImpl()
             }
            return operation
        }
    }

}
