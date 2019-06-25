package com.linhuanjie.thread;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-05-22 19:58
 * @email: lhuanjie@qq.com
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        // do something
        System.out.println("当前线程：" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
