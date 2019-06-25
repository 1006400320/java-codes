package com.linhuanjie.javase;

public class StringTest {


    public static void main(String[] args) {
        char [] c = new char[]{'w','e','q','r','u','r'};
        String s = new String(c,1,3);

        System.out.println(s);
    }
}
