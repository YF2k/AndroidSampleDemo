package com.joker.demo.kotlin


fun main() {
    val a = 10
    val b: Int = 20;
    println("a=" + a)
    var c = add(a, b)
    println("c=" + c)
    c = add1(a, b)
    println("c=" + c)
    c = add2(a, b)
    println("c=" + c)
    c = largerNum(a, b)
    println("c=" + c)
    c = largerNum1(a, b)
    println("c=" + c)
    c = getScore("Tom")
    println("c=" + c)

    //对象
    val p = Person()
    p.name = "Jack"
    p.age = 19
    p.eat()

    val student = Student("a123", 5);


    val student1 = Student1("a123", 5, "Jack", 19)

    val student2 = Student4("Jack", 19)
    doStudy(student2)

    //数据类  如果将 class 前面的 data 去掉，那么它们的返回值就会变为 false
    val cellphone1 = Cellphone("Samsung", 1299.99)
    val cellphone2 = Cellphone("Samsung", 1299.99)
    println(cellphone1)
    println("cellphone1 equals cellphone2 " + (cellphone1 == cellphone2))

    Singleton.singletonTest()

    lamadaTest()

//字符串内嵌表达式
//"hello, ${obj.name}. nice to meet you!"
    val brand = "Samsung"
    val price = 1299.00
    println("Cellphone(brand=$brand, price=$price)")    // 使用字符串表达式
    println("Cellphone(brand = " + brand + ", price = " + price + ")")    // 不使用

    printParams(1)
    printParams(1, "哈哈")

    printParams1(str = "world")

    //扩展函数
    println("Kotlin".lastChar())
}


//函数
fun add(a: Int, b: Int): Int {
    return a + b
}

//语法糖
fun add1(a: Int, b: Int): Int = a + b

fun add2(a: Int, b: Int) = a + b

//if
fun largerNum(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

//精简
fun largerNum1(a: Int, b: Int) = if (a > b) a else b

//when 匹配值 -> {执行逻辑}
fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 98
    else -> 0
}

fun checkNumber(num: Number) {
    when (num) {
        is Int -> print("number is Int")
        is Double -> print("number is Double")
        else -> print("number not support")
    }
}

/**
 * 不常用写法
 */
fun getScore1(name: String) = when {
    name == "Tom" -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}

//for-in循环

fun testforIn() {
    //[0, 10]
    val range = 0..10
    for (i in range) {
        println(i)
    }
    //[0, 10)
    for (i in 0 until 10) {
        println(i)
    }
    //默认情况下，i 会每次自增 1，如果想让 i 一次加 2 的话可以使用 step 2实现，3，4，5.。。n 也是同样的道理。
    for (i in 0 until 10 step 2) {
        println(i)
    }

    //注意：在进行遍历时左边的数值必须小于右边的数值，如果想实现降序的话要使用downTo替代
    for (i in 20 downTo 10 step 2) {
        println(i)
    }
}

//面向对象
open class Person {
    var name = ""
    var age = 0
    fun eat() {
        println(name + " is eating. He is " + age + " years old")
    }
}

//继承  在 Kotlin 中使用 : 代替，被继承的类必须要调用它的构造函数，否则会报错
//主构造函数
class Student(val sno: String, val grade: Int) : Person() {
    //    var sno=""
//    var grade=0
    //将主构造函数的逻辑写在 init 结构体中
    init {
        println("sno is " + sno)
        println("grade is " + grade)
    }
}

//注意：在 Student 的主构造函数中添加 name 和 age 字段时，
// 不能再将它们声明为 val，
// 因为在主构造函数中声明成 val 或者 var 的参数会自动成为该类的字段，
// 这回导致和父类中同名的 name 和 age 字段造成冲突，
// 因此在这里的 name 和 age 前面不需要加任何关键字，
// 让它的作用域仅限定在主构造函数中即可。
open class Person1(val name: String, val age: Int) {

}

class Student1(val sno: String, val grade: Int, name: String, age: Int)
    : Person1(name, age) {
}

//次构造函数
class Student2(val sno: String, val grade: Int, name: String, age: Int)
    : Person1(name, age) {
    constructor(name: String, age: Int) : this("", 0, name, age) {
    }

    constructor() : this("", 0) {
    }
}

//这里的 Student 类的后面没有显式的定义主构造函数，同时又因为定义了次构造函数，
// 所以现在 Student 类是没有主构造函数的，那么在继承 Person 类是就不需要再添加括号了，
// 另外由于没有主构造函数，次构造函数只能显式的调用父类的构造函数，所以可以将 this 换成 super

//不设置主构造函数
class Student3 : Person1 {
    constructor(name: String, age: Int) : super(name, age) {

    }
}

//接口
interface Study {
    fun readBooks()
    fun doHomework() {
        println("do homework default implementation.")
    }
}

//在 Java 中实现接口使用 implements 关键字，在 Kotlin 中无论是继承还是实现接口都是用 “:” 替代，
// 中间使用逗号（,）隔开即可，另外在实现接口时不需要在接口后面加括号，因为接口没有构造函数。
class Student4(name: String, age: Int) : Person1(name, age), Study {
    override fun readBooks() {
        println(name + " is reading.")
    }

    override fun doHomework() {
        println(name + " is doing homework")
    }
}

fun doStudy(study: Study) {
    study.readBooks()
    study.doHomework()
}

//数据类
data class Cellphone(val brand: String, val price: Double)

//单例类
object Singleton {
    fun singletonTest() {
        println("singletonTest is called.")
    }
}

//lamada表达式
fun lamadaTest() {
    //在这里使用 listOf()函数创建的是一个不可变的集合。 在 Java 中没有不可变的集合，但是在 Kotlin 中不可变的集合指的是，
// 该集合中的元素只能用于读取，不能进行添加、修改或者删除。
    //不可变集合
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    for (fruit in list) {
        println(fruit)
    }

    //可变集合
    val mutablelist = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
    mutablelist.add("Watermelon")
    for (fruit in list) {
        println(fruit)
    }

    val set = setOf("Apple", "Banana", "Orange", "Pear", "Grape")
    for (fruit in set) {
        println(fruit)
    }

    println("==========================")
    val mutableSet = mutableSetOf("Apple", "Banana", "Orange", "Pear", "Grape")
    mutableSet.add("Watermelon")
    for (fruit in mutableSet) {
        println(fruit)
    }

    //map
    val map = HashMap<String, Int>()
    map["Apple"] = 1
    map["Banana"] = 2
    map["Orange"] = 3
    map["Pear"] = 4
    map["Grape"] = 5

    //精简
    val map1 = mapOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)
//    for (entry in map) {
//        println(entry.key + "\t" + entry.value)
//    }
    for ((fruit, number) in map1) {
        println("fruit is " + fruit + ", number is " + number)
    }

//    找到单词最长的水果
    val list3 = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val maxLengthFruit = list3.maxBy { it.length }
    println("max length fruit is " + maxLengthFruit)


    //Lambda 表达式语法结构
//{参数名1： 参数类型, 参数名2: 参数类型 -> 函数体}

    val list5 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val lambda = { fruit: String -> fruit.length }
    val maxLengthFruit5 = list5.maxBy(lambda)
//    精简
    val list6 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit6 = list6.maxBy({ fruit: String -> fruit.length })
//精简   Kotlin 规定当函数的最后一个参数是 Lambda 时，可以将 Lambda 表达式写在最外面.
    val list7 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit7 = list7.maxBy() { fruit: String -> fruit.length }
//    精简  当 Lambda 参数是函数的唯一一个参数的话，可以省略函数的括号。
    val list8 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit8 = list8.maxBy { fruit: String -> fruit.length }
//    精简   由于 Kotlin 的推导机制，Lambda 的参数列表在大多数情况下不必声明参数类型，因此代码可以进一步简化
    val list9 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit9 = list9.maxBy { fruit -> fruit.length }
//    精简  当 Lambda 表达式的参数列表中只有一个参数时，可以不必声明参数名，可以用 it 代替
    val list10 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit10 = list10.maxBy { it.length }


// 使用map函数
//    让所有的水果命都变成大写模式
    val list11 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val newList = list11.map { it.toUpperCase() }
    for (fruit in newList) {
        println(fruit)
    }

//使用 filter 函数
//    只保留集合中字符长度大于5的水果名，并将符合条件的水果名转换为大写
    val list12 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val newList2 = list12.filter { it.length <= 5 }.map { it.toUpperCase() }
    for (fruit in newList) {
        println(fruit)
    }

//    使用 any 和 all 函数
//    any 函数用于判断集合种是否至少存在一个元素满足指定条件，all 函数用于判断集合中是否所有元素都满足给定条件。
    val list13 = listOf("Apple", "Orange", "Pear", "Grape", "Watermelon")
    val anyResult = list13.any { it.length <= 5 }
    val allResult = list13.all { it.length <= 5 }
    println("anyResult is " + anyResult + ", allResult is " + allResult)

//Java 函数式 API 的使用
//    单抽象接口
//    如果我们再 Kotlin 代码中调用了一个 Java 方法，并且该方法接收一个 Java 单抽象方法接口参数，就可以使用函数式 API。


/*java中
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}*/
    //    对于任何一个 Java 方法，只要它接收 Runnable 参数，就可以使用函数时 API。不过 Runnable 接口主要还是结合线程来一起使用的，因此这里就通过 Java 的线程类 Thread 进行学习。
    /* new Thread(new Runnable() {
         @Override
         public void run() {
             System.out.println("Thread is running.");
         }
     }).start();*/
//与 Java 写法不同的是，Kotlin 中使用 object 关键字代替了 new 关键字
    Thread(object : Runnable {
        override fun run() {
            println("Thread is running.")
        }
    }).start()
//简化
//    由于 Runnable 接口中只有一个方法，所以没有手动实现的话，Kotlin 就会推导出 Lambda 表达式里要写的是 run() 方法中的内容。
    Thread(Runnable {
        println("Thread is running.")
    }).start()
//简化

//由于 Java 方法的参数列表中不存在一个以上 Java 单抽象方法接口参数，所以可以将接口名省略。
    Thread({
        println("Thread is running.")
    }).start()

//    由于 Lambda 中只有一个参数，所以可以将括号花括号内的内容移动到外面，并且还可以将函数的括号省略，所以简写成如下形式：
    Thread {
        println("Thread is running.")
    }.start()

}

//空指针检查
fun doStudy1(study: Study?) {
    if (study != null) {
        study.readBooks()
        study.doHomework()
    }
}

//简化   ?. 操作符 当对象不为空时进行正常调用，为空就什么都不做
fun doStudy2(study: Study?) {
    study?.readBooks()
    study?.doHomework()


//    ?: 操作符  这个操作符两边都接收一个表达式，如果左边表达式的结果不为空就返回左边的结果，否则返回右边的。
    var a = null
    var b = 1
    val c = if (a != null) {
        a
    } else {
        b
    }
//简化
    val c1 = a ?: b

}


fun getTextLength1(text: String?): Int {
    if (text != null) {
        return text.length
    }
    return 0
}

fun getTextLength(text: String?) = text?.length ?: 0

//!!. 操作符
//Kotlin 有的时候也不很智能，比如已经做了非空判断，但是调用时依然无法通过编译，那么此时可以使用非空断言工具!!。即可。
//
//注意：这种写法存在风险，这样写意在告诉 Kotlin，我这里一定不为空，如果为空后果我自己承担。


// let 函数
//let 函数提供了函数式 API 的编程接口，并将原始调用对象作为参数传递到 Lambda 表达式中。

/*obj.let { obj2 ->
    // 编写具体的业务逻辑
}*/
fun doStudy3(study: Study?) {
    study?.readBooks()
    study?.doHomework()
}

//优化
fun doStudy4(study: Study?) {
    study?.let { stu ->
        stu.readBooks()
        stu.doHomework()
    }
}

//优化  在 Kotlin 中，Lambda 表达式如果只有一个参数，可以省略，使用 it 代替。
fun doStudy5(study: Study?) {
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}


//函数的参数默认值---经常可以代替次构造函数
fun printParams(num: Int, str: String = "hello") {
    println("num is $num, str is $str")
}


fun printParams1(num: Int = 100, str: String) {
    println("num is $num, str is $str")
}


class Student9(val sno: String = "", val grade: Int = 0, name: String = "", age: Int = 0) :
        Person1(name, age) {
}

//扩展函数---可以让你作为一个类成员进行调用的函数，但是是定义在这个类的外部
fun String.lastChar(): Char = this.get(this.length - 1)






