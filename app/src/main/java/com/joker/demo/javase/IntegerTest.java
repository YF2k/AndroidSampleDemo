package com.joker.demo.javase;

//参考：https://www.jianshu.com/p/5b2837e063fd
public class IntegerTest {

    //结果说明 在值域为 [-128,127]之间，用==符号来比较Integer的值，是相等的
    public static void main(String[] args) {
        Integer a=125;
        Integer b=125;
        System.out.println(a==b);

        a=200;
        b=200;
        System.out.println(a==b);
        System.out.println(a.equals(b));


    }

    /* public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }*/


}
