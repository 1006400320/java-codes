package com.linhuanjie.javase;


import java.text.MessageFormat;

/**
 * @author linhuanjie@xmiles.cn
 * @date 2018/10/30.
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        String a= "aaa";
        String b= "bb";
        String c= "c";
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(b).append(c);
        System.out.println(sb.toString());
        System.out.println(MessageFormat.format(" {0} {1} {2} {3}", a, b,"|",sb));
        System.out.println(MessageFormat.format(" ''{0}'' '{1}' {2} {3}", a, b,"",sb.toString()));


        System.out.println(MessageFormat.format("aaa_{0}", "结果"));

    }
}
