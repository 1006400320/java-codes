package com.linhuanjie.admin;

import java.util.concurrent.ExecutionException;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019.07.30 21:20
 * @email: lhuanjie@qq.com
 */
public class TestDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Object o = new Object();
        System.out.println(o.getClass().getClassLoader());

        System.out.println();
        System.out.println();
        System.out.println();

        TestDemo d = new TestDemo();
        System.out.println(d.getClass().getClassLoader().getParent().getParent());
        System.out.println(d.getClass().getClassLoader().getParent());
        System.out.println(d.getClass().getClassLoader());

    }
}
