package com.linhuanjie.thread;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-05-22 20:02
 * @email: lhuanjie@qq.com
 */
public class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("当前线程：" + Thread.currentThread());
    }

    public static void main(String[] args) {
        new Thread(new MyThread2()).start();
    }
}
