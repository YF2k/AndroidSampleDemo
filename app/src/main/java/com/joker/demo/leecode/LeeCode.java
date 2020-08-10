package com.joker.demo.leecode;

import java.util.Arrays;
import java.util.Comparator;

public class LeeCode {

    public static void main(String[] args) {
        //ex1:
        String[] dictionary={"vprkj","sqvuzjz","ptkrqrkussszzprkqrjrtzzvrkrrrskkrrursqdqpp","spqzqtrqs","rkktkruzsjkrzqq","rk","k","zkvdzqrzpkrukdqrqjzkrqrzzkkrr","pzpstvqzrzprqkkkd","jvutvjtktqvvdkzujkq","r","pspkr","tdkkktdsrkzpzpuzvszzzzdjj","zk","pqkjkzpvdpktzskdkvzjkkj","sr","zqjkzksvkvvrsjrjkkjkztrpuzrqrqvvpkutqkrrqpzu"};
        String sentence="rkktkruzsjkrzqqzkvdzqrzpkrukdqrqjzkrqrzzkkrr";

        String[] dictionary2={"bt","vbtbvtvvbbvtbvvbbbvbtbbv","bvvbbbvvvbvttbtbvtvtvvbttbbbtvvvb","btttbvbbbtbbtbvvttbvbvtvbtbbttb","bt","vvbvbvbvtbvbvvvvtv","tbvvvtttvtbvbtttvvbtvvvvtvvbvvtvvbbvbbbvb","v","bvb","vvtbvtvbttbttvvbvttbt","btbtbtttvbbtbttbtvvttbvtbtvtbvvtbbbb","bttbvbbttvvbtvvvvb","bvvbvbvttbvtbvvtbttvvvvtvbtvbttbbvvtvtvv","vbtttt","btbvbbbvbtvtbvvv","b","tbtbtv","vbvbbvvbvbbvvb","vbvvtvbvbvbttvbvbtvbtbtvtbvbbtb","bvvbvvttttttbtvvvttvbvtvvbvtbtvtbvttvvtbt","bvtbttv","bbbt","vtt","ttvv","b","vbb","vtvvbtttvtbbvvbbtbb","vvv","vvvvbbbtbbbvbbbb","ttvvbtvv","v","btvbbvtbbvbvtvttvvbbbtbvvvtbtb","vv","btbttbtbbvbvvbvttbttvtbbtbvtttvbbtbbtvtvvvvbbttvtvvbbbv","ttvbbbbttbtbtb","tvvbvbvvb","vv","ttbttvtvbtbbbbv","bvvvtbbvvvbtvbvtvtvvvvbb","vtbvvbvvvvvttvbbttbbvtvt","tbvbbt","b","v","tvbbtvvtvvtbtbttvvvb","tbttbttbbbtbtvtvtvtbbbvvtbbbvbbvvvbbttvvt","bbvttvtvvtbvbbttvbbtbvvttbvbvbtbvvvbbbvbvbvbtvbtvvvtvvtbttbttbbvbbbttvvvbvvtb","vttvvbvv","tbttbvvttvbtvvtbbvvv","bvbbbbbbbb","vtbvvtbbvtt","bvttbvvbvb","tvttttbbvvvbbtttvvv"};
        String sentence2="btbvtttttbvttbvvbbtvvbvbvvbtbtbtvbtttbvbbbtbbtbvvttbvbvtvbtbbttbvvbvbtttbvttbvvbbvvv";
//        System.out.println(duanju(dictionary2,sentence2));
        duanju2(dictionary2,sentence2);
    }


    public static int duanju(String[] dictionary,String sentence){
        String temp=sentence;
        //字典按照字母长度排序-----不排序的话ex1中borther和her在统计时会出问题，
        Arrays.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return   o1.length()>o2.length()?-1:1;
            }
        });

        for(int i=0;i<dictionary.length;i++){
            if( temp.contains(dictionary[i])){
                System.out.println("dictionary----------"+dictionary[i]);
                temp=temp.replace(dictionary[i],"");//bug---字符串重新拼接后，有可能导致拼接后的字符串出现了新单词
                System.out.println("temp="+temp);
            }
        }
        System.out.println(temp);
        return temp.length();

    }


    public static void duanju2(String[] dictionary,String sentence){
        String temp=sentence;
        int count=0;
        //字典按照字母长度排序
        Arrays.sort(dictionary);
        for(int i=0;i<dictionary.length;i++){
            if( temp.contains(dictionary[i])){
                count++;
                temp=temp.replace(dictionary[i],"@");
            }
        }
        String[] arr=temp.split("@");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
            if(!arr[i].equals("")){
//                System.out.println(arr[i]);
                count++;
            }
        }
        System.out.println(temp);
        System.out.println(count);
    }
}
