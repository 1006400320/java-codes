package com.linhuanjie.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: linhuanjie
 * @description: Executor执行Runnable任务
 * @createTime : 2019-05-22 20:16
 * @email: lhuanjie@qq.com
 */
public class Executor1{

    public static void main(String[] args){
        // 使用newCachedThreadPool(),
        // execute会首先在线程池中选择一个已有空闲线程来执行任务，如果线程池中没有空闲线程，它便会创建一个新的线程来执行任务。
        ExecutorService executorService = Executors.newCachedThreadPool();
//      ExecutorService executorService = Executors.newFixedThreadPool(5);
//      ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++){
            executorService.execute(new TestRunnable());
            System.out.println("************* a" + i + " *************");
        }
        executorService.shutdown();
    }
}

class TestRunnable implements Runnable{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "线程被调用了。");
    }
}