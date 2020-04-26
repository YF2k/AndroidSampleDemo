package com.joker.demo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testByte(){
        int a=129;
        String s="128";
        byte b= (byte) a;
        byte d= Byte.parseByte(s);
        System.out.println(b);
        System.out.println(d);
    }
}