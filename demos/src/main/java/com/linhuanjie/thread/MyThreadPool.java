package com.linhuanjie.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-05-22 21:30
 * @email: lhuanjie@qq.com
 */
public class MyThreadPool {
    public static void main(String[] args){
        //创建等待队列
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
        //创建线程池，池中保存的线程数为3，允许的最大线程数为5
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50, TimeUnit.MILLISECONDS,bqueue);
        //创建七个任务
        for (int i = 0; i < 7; i++) {
            //每个任务会在一个线程上执行
            pool.execute(new MyThreadDemo());
        }

        //关闭线程池
        pool.shutdown();
    }
}


class MyThreadDemo implements Runnable{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}