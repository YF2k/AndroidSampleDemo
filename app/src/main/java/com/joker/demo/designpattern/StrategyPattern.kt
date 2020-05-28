package com.joker.demo.designpattern

//策略模式定义了一系列算法，并将每一个算法封装起来，而且使他们还可以相互替换，策略模式让算法独立于使用它的客户而独立变化

//使用场景：针对同一类型问题的多种处理方式，仅仅是具体行为有差别时。
//需要安全的封装多种同一类型操作时。
//出现同一抽象类有多个子类，而又需要使用 if-else 或者 switch-case 来选择具体子类时。
fun main() {
    var transportationCalculator=TransportationCalculator()
    System.out.println("普通车 5 km RMB:" + transportationCalculator.calcu(5));

    transportationCalculator.setStrategy(MediumCar())
    System.out.println("普通车 5 km RMB:" + transportationCalculator.calcu(5));

    transportationCalculator.setStrategy(LuxuryCar())
    System.out.println("普通车 5 km RMB:" + transportationCalculator.calcu(5));

}

interface ICalculateStrategy {
    /**
     * 按距离计算车费
     * @param km  公里数
     * @return
     */
    fun calculatePrice(km: Int):Int
}

//普通轿车
class GeneralCar : ICalculateStrategy {
    override fun calculatePrice(km: Int):Int {
        if (km <= 5) {
            return 2
        }else if(km>5&&km<=10){
            return 3
        }
        return 4
    }

}


//中等轿车
class MediumCar : ICalculateStrategy {
    override fun calculatePrice(km: Int):Int {
        if (km <= 5) {
            return 5
        }else if(km>5&&km<=10){
            return 6
        }
        return 7
    }

}


//高级轿车
class LuxuryCar : ICalculateStrategy {
    override fun calculatePrice(km: Int):Int {
        if (km <= 5) {
            return 8
        }else if(km>5&&km<=10){
            return 9
        }
        return 10
    }

}

class TransportationCalculator{
    /**
     * 交通工具计算费用策略类
     */
     var iCalculateStrategy:ICalculateStrategy=GeneralCar();
    /**
     * 设置策略
     * @param calculateStrategy
     */
    fun setStrategy(iCalculateStrategy: ICalculateStrategy){
        this.iCalculateStrategy= iCalculateStrategy
    }

    fun calcu(km:Int):Int{
        return iCalculateStrategy.calculatePrice(km)
    }

}