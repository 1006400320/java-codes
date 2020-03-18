package com.linhuanjie.reflection;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 三种获取Class方式
 * @CreateTime : 2019.11.20 23:01
 **/
public class GetClassDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        String a = "QAQ";

        Class<? extends String> clazz1 = a.getClass();
        Class<?> clazz2 = Class.forName(a);
        Class<String> clazz3 = String.class;
    }
}
