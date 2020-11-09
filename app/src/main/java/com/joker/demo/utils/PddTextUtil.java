package com.joker.demo.utils;

import java.util.Random;

public class PddTextUtil {
    public static String GetRandstr(int count, int char_type) {
        char[] constant = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        StringBuilder sb = new StringBuilder(62);
        Random rd = new Random();
        for (int i = 0; i < count; i++) {
            char ch = ' ';

            if (i == 0) {
                ch = constant[getRandom(9, constant.length - 1)];
            } else {
                ch = constant[rd.nextInt(constant.length - 1)];

            }

            if (char_type == 2) {
                int num = getRandom(1, 2);

                if (num == 2) {
                    sb.append(String.valueOf(ch).toUpperCase());
                } else {
                    sb.append(ch);
                }
            } else {
                sb.append(ch);
            }
        }

        if (char_type == 1) {
            return sb.toString().toUpperCase();
        }

        return sb.toString().toUpperCase();
    }

    public static int getRandom(int min, int max) {
        Random random = new Random();
        int i = random.nextInt(max) % (max - min + 1) + min;
        return i;
    }
}
