package com.joker.demo.designpattern

//抽象工厂模式---提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类
/*抽象工厂角色（Abstract Factory）：具体工厂（产品族）公共接口

具体工厂角色（Concrete Factory）：产品族

抽象产品角色（Abstract Product）：产品等级结构

具体产品角色（Concrete Product）：产品*/
fun main(){

    var factory=ChenGuangFactory()
    var pencil= factory.createPencil()
    pencil.draw()
    var eraser=factory.createEraser()
    eraser.erase()

    var factory2 = TrueColorFactory()
    val pencilT = factory2.createPencil()
    pencilT.draw() // 用真彩铅笔画图。

    val eraserT = factory2.createEraser()
    eraserT.erase() // 用真彩橡皮擦除真彩铅笔画的图。

}


//抽象工厂
interface  AbstractFactory{
    fun createPencil():Pencil
    fun createEraser():Eraser
}

//具体工厂
class ChenGuangFactory :AbstractFactory{
    override fun createPencil(): Pencil {
        return ChenGuangPencil()
    }

    override fun createEraser(): Eraser {
        return ChenGuangEraser()
    }

}

class TrueColorFactory :AbstractFactory{
    override fun createPencil(): Pencil {
        return TrueColorPencil()
    }

    override fun createEraser(): Eraser {
        return TrueColorEraser()
    }

}

//抽象产品
interface Pencil{
    fun draw()
}

interface Eraser{
    fun erase()
}

//具体产品
class ChenGuangPencil :Pencil{
    override fun draw() {
        System.out.println("用晨光铅笔画图。");
    }

}

class TrueColorPencil :Pencil{
    override fun draw() {
        System.out.println("用真彩铅笔画图。");
    }

}

class ChenGuangEraser:Eraser{
    override fun erase() {
        System.out.println("用晨光橡皮擦除晨光铅笔画的图。");
    }

}

class TrueColorEraser:Eraser{
    override fun erase() {
        System.out.println("用真彩橡皮擦除真彩铅笔画的图。");
    }

}