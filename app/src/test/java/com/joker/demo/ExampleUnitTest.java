package com.joker.demo;

import org.json.JSONArray;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    String userHome = System.getProperty("user.home");
    String url = userHome + "/360jiagu";//apk包位置

    private String myFile = userHome + "/channel_data.txt";
    private String outFile = userHome + "/channel_out.txt";
    private String appVersion = "3.16.0";

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testByte() {
        int a = 129;
        String s = "128";
        byte b = (byte) a;
        byte d = Byte.parseByte(s);
        System.out.println(b);
        System.out.println(d);
    }

    @Test
    public void select() {
        String str = null;
        List channels = new ArrayList<String>();
        File file = new File(myFile);
        if (file.exists()) {
            try {
                InputStreamReader fileInputStream = new InputStreamReader(new FileInputStream(file));
                BufferedReader bis = new BufferedReader(fileInputStream);
                while ((str = bis.readLine()) != null) {

                    String channel = str.split(" ")[1];
                    channels.add(channel);
//                    System.out.println(channel);

                }

                fileInputStream.close();
                bis.close();
                for (int i = 0; i <= channels.size() - 2; i++) {
                    for (int j = i + 1; j <= channels.size() - 1; j++) {
                        if (channels.get(i).equals(channels.get(j))) {
                            System.out.println(channels.get(i));
                        }
                    }

                }

            } catch (Exception e) {

            }

        }
    }

    @Test
    public void iteratorRemove() {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        Iterator<Integer> iterator = nums.iterator();
        while (iterator.hasNext()) {
            Integer num= iterator.next();
            System.out.println(num);
            if(num==2){
                iterator.remove();
                System.out.println(num);
                break;
            }
        }

        for(Integer i:nums){
            System.out.println(i);
        }
    }


    @Test
    public void sha1Encode(){
        String str="lsi1111111111111111djf";
        try {
            System.out.println(str);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            System.out.println("sha1加密串："+messageDigest.digest());
            System.out.println("sha1加密串16进制："+getFormattedText(messageDigest.digest()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private  final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private  String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    @Test
    public void testHashMap(){
        HashMap<String,HashMap<String,String>> map=new HashMap<>();
        HashMap<String,String> data=new HashMap<>();
        data.put("li","haha");
        data.put("xiang","haha");
        HashMap<String,String> data1=new HashMap<>();
        data1.put("li1","haha");
        data1.put("xiang1","haha");
        map.put("data",data);
        map.put("data1",data1);
        System.out.println(map.toString());
    }
}