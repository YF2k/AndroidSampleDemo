package com.joker.demo.designpattern

//构造者模式---一个产品有不同的组成部分，而在程序里面需要把一个产品抽检出来需要较多的零件进行处理和组装，使得构造一个产品变得非常复杂，构造模式就是使得客户在不需要知道这么多复杂的细节，不必关心产品内部细节
//https://www.jianshu.com/p/f36dbec7adb4
fun main(){

}

//抽象产品
abstract class AProduct{
    //公共部分
}

//具体产品
class ProductA : AProduct(){

}

class ProductB :AProduct(){

}

//抽象构造者
interface IBuilder{
    fun setOne()
    fun setTwo()
    fun build():AProduct
}
//具体构造者
class BuilderOne: IBuilder{
    var product=ProductA()
    override fun setOne() {
        //打造第一个零件
    }

    override fun setTwo() {
        //打造第二个零件
    }

    override fun build():AProduct{
        //组装出产品1
        return product
    }

}

class BuilderTwo: IBuilder{
    var product=ProductB()
    override fun setOne() {
        //打造第一个零件
    }

    override fun setTwo() {
        //打造第二个零件
    }

    override fun build():AProduct{
        //组装出产品2
        return product
    }

}

//导演角色
class Director(){
    var builder:IBuilder?=null
    //这里可以结合工厂模式
    fun construct(type:Int){
        if(type==1){
            builder=BuilderOne()
            builder?.setOne()
            builder?.setTwo()
            builder?.build()

        }else{
            builder=BuilderTwo()
            builder?.setOne()
            builder?.setTwo()
            builder?.build()
        }
    }

}


