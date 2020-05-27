package com.joker.demo.designpattern

//模板方法模式--定义一个操作中算法的框架，将一些步骤交于子类具体实现，使得子类可以改变一个算法的结构即可重定义该算法的某些特定步骤
//需求： 用代码简要描述开机过程
fun main(){
    val abstractComputerA = CoderComputer()
    abstractComputerA.startUp();

    val abstractComputerB =  MilitaryComputer();
    abstractComputerB.startUp();

}
//定义抽象的模板
abstract class AbstractComputer{
    /**
     * 开启电源
     */
    open fun powerOn(){
        println("开启电源")
    }

    /**
     * 硬件检查
     */
    open fun checkHardware() {
        println("硬件检查")
    }
    /**
     * 加载系统
     */
    open fun loadSystem(){
        println("加载系统");
    }


    /**
     * 屏幕显示
     */
    open fun showScreen(){
        println("屏幕显示");
    }

    /**
     * 输入用户信息
     */
    open fun login(){
        println("密码验证成功，进入主页面。");
    }

    /**
     * 启动计算机方法，步骤固定为开启电源、硬件检查、加载系统、屏幕显示、登录。设计为 final 防止被重写
     */

     fun startUp(){
        System.out.println("------>>>>>>>   startup  ");
        powerOn();
        checkHardware();
        loadSystem();
        showScreen();
        login();
        System.out.println("------>>>>>>>   Successful");

    }

}

//抽象模板实现 A
class CoderComputer : AbstractComputer() {
    override fun login() {
        println("密码输入完成，交于系统检查。");
        super.login()
    }

}
//抽象模板实现 B
class MilitaryComputer : AbstractComputer() {
     override fun checkHardware() {
        super.checkHardware()
        println("检查防火墙")
    }

    override fun login() {
        println("视网膜扫描 \n 视网膜验证成功");
        super.login()
    }

}

