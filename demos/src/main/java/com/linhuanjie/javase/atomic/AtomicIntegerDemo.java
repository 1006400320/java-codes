package com.linhuanjie.javase.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: linhuanjie
 * @description: 利用AtomicInteger模拟卖票程序，运行结果中不会出现两个程序卖了同一张票，也不会卖到票为负数
 * @createTime : 2019-04-04 16:13
 * @email: lhuanjie@qq.com
 */
public class AtomicIntegerDemo {

    AtomicInteger tickets = new AtomicInteger(100);
    class Seller implements Runnable{
        @Override
        public void run() {
            while(tickets.get() > 0){
                int tmp = tickets.get();
                if(tickets.compareAndSet(tmp, tmp-1)){
                    System.out.println(Thread.currentThread().getName()+" "+tmp);
                }
            }
        }
    }
    public static void main(String[] args) {
        AtomicIntegerDemo st = new AtomicIntegerDemo();
        new Thread(st.new Seller(), "SellerA").start();
        new Thread(st.new Seller(), "SellerB").start();
    }

}
