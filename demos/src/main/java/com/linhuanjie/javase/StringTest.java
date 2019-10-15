package com.linhuanjie.javase;

public class StringTest {


    public static void main(String[] args) {
//        char [] c = new char[]{'w','e','q','r','u','r'};
//        String s = new String(c,1,3);
//
//        System.out.println(s);

        String str1 = "abc";
        String str2 = "abc";
        String str3 = str1;
        String str4 = new String("abc");


        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str3);
        System.out.println(str1 == str4);
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        System.out.println(str3.hashCode());
        System.out.println(str4.hashCode());
    }
}
