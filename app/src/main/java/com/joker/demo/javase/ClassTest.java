package com.joker.demo.javase;

import java.io.Serializable;

//编译命令：javac ClassTest.java
public class ClassTest implements Serializable, Cloneable {
    private int num = 1;

    public int add(int i) {
        int j = 10;
        num = num + i;
        return num;
    }

}

/*ClassTest.class:用16进制编辑器打开：（16进制每两个字符代表一个字节）
cafe babe 0000 0034 0017 0a00 0400 1109
0003 0012 0700 1307 0014 0700 1507 0016
0100 036e 756d 0100 0149 0100 063c 696e
6974 3e01 0003 2829 5601 0004 436f 6465
0100 0f4c 696e 654e 756d 6265 7254 6162
6c65 0100 0361 6464 0100 0428 4929 4901
000a 536f 7572 6365 4669 6c65 0100 0e43
6c61 7373 5465 7374 2e6a 6176 610c 0009
000a 0c00 0700 0801 001f 636f 6d2f 6a6f
6b65 722f 6465 6d6f 2f6a 6176 6173 652f
436c 6173 7354 6573 7401 0010 6a61 7661
2f6c 616e 672f 4f62 6a65 6374 0100 146a
6176 612f 696f 2f53 6572 6961 6c69 7a61
626c 6501 0013 6a61 7661 2f6c 616e 672f
436c 6f6e 6561 626c 6500 2100 0300 0400
0200 0500 0600 0100 0200 0700 0800 0000
0200 0100 0900 0a00 0100 0b00 0000 2600
0200 0100 0000 0a2a b700 012a 04b5 0002
b100 0000 0100 0c00 0000 0a00 0200 0000
0500 0400 0600 0100 0d00 0e00 0100 0b00
0000 3200 0300 0300 0000 1210 0a3d 2a2a
b400 021b 60b5 0002 2ab4 0002 ac00 0000
0100 0c00 0000 0e00 0300 0000 0900 0300
0a00 0d00 0b00 0100 0f00 0000 0200 10

魔数：ca fe ba be
版本号：00 00 00 34
常量池（重点）：
0017：常量池大小 ----转换成10进制为23，因为0被JVM用作其他用途,因此 Test.class 中实际的常量池大小为这个计数器的值减 1，也就是 22个
第一个常量：[0a:-----转换成10进制为10,通过查看常量池 14 种表格图中，可以查到 tag=10 的表类型为 CONSTANT_Methodref_info即方法引用表

方法引用表表结构：
CONSTANT_Methodref_info {
    u1 tag = 10;
    u2 class_index;        指向此方法的所属类
    u2 name_type_index;    指向此方法的名称和类型

}
也就是说在“0a”之后的 2 个字节指向这个方法是属于哪个类，紧接的 2 个字节指向这个方法的名称和类型。它们的值分别是：

0004：十进制 4，表示指向常量池中的第 4 个常量。

0011：十进制 17，表示指向常量池中的第 17 个常量。
至此，第 1 个常量就解读完毕了
]

访问标志：占用两个字节：访问标志代表类或者接口的访问信息，比如：该 class 文件是类还是接口，是否被定义成 public，是否是 abstract，如果是类，是否被声明成 final 等等

类索引、父类索引与接口索引计数器

字段表：字段表的主要功能是用来描述类或者接口中声明的变量。这里的字段包含了类级别变量以及实例变量，但是不包括方法内部声明的局部变量













* \*/


//实际上我们可以借助 javap 命令来帮助我们查看 class 常量池中的内容
/*

localhost:~ lee$ javap -v file:/Users/lee/Demo/app/src/main/java/com/joker/demo/javase/ClassTest.class
Classfile /Users/lee/Demo/app/src/main/java/com/joker/demo/javase/ClassTest.class
Last modified 2020-8-11; size 383 bytes
        MD5 checksum fc66754d65a8a600e2c85edb90cff110
        Compiled from "ClassTest.java"
public class com.joker.demo.javase.ClassTest implements java.io.Serializable,java.lang.Cloneable
        minor version: 0
        major version: 52
        flags: ACC_PUBLIC, ACC_SUPER
        Constant pool:
        #1 = Methodref          #4.#17         // java/lang/Object."<init>":()V
        #2 = Fieldref           #3.#18         // com/joker/demo/javase/ClassTest.num:I
        #3 = Class              #19            // com/joker/demo/javase/ClassTest
        #4 = Class              #20            // java/lang/Object
        #5 = Class              #21            // java/io/Serializable
        #6 = Class              #22            // java/lang/Cloneable
        #7 = Utf8               num
        #8 = Utf8               I
        #9 = Utf8               <init>
        #10 = Utf8               ()V
          #11 = Utf8               Code
          #12 = Utf8               LineNumberTable
          #13 = Utf8               add
          #14 = Utf8               (I)I
          #15 = Utf8               SourceFile
          #16 = Utf8               ClassTest.java
          #17 = NameAndType        #9:#10         // "<init>":()V
          #18 = NameAndType        #7:#8          // num:I
          #19 = Utf8               com/joker/demo/javase/ClassTest
          #20 = Utf8               java/lang/Object
          #21 = Utf8               java/io/Serializable
          #22 = Utf8               java/lang/Cloneable
          {
public com.joker.demo.javase.ClassTest();
        descriptor: ()V
        flags: ACC_PUBLIC
        Code:
        stack=2, locals=1, args_size=1
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: aload_0
        5: iconst_1
        6: putfield      #2                  // Field num:I
        9: return
        LineNumberTable:
        line 5: 0
        line 6: 4

public int add(int);
        descriptor: (I)I
        flags: ACC_PUBLIC
        Code:
        stack=3, locals=3, args_size=2
        0: bipush        10
        2: istore_2
        3: aload_0
        4: aload_0
        5: getfield      #2                  // Field num:I
        8: iload_1
        9: iadd
        10: putfield      #2                  // Field num:I
        13: aload_0
        14: getfield      #2                  // Field num:I
        17: ireturn
        LineNumberTable:
        line 9: 0
        line 10: 3
        line 11: 13
        }
        SourceFile: "ClassTest.java"
*/
