package com.linhuanjie.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-9-11 18:46
 * @email: lhuanjie@qq.com
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolDemo demo = new ThreadPoolDemo();
//        demo.cachedThreadPoolDemo();
//        demo.fixedThreadPoolDemo();
//        demo.scheduledThreadPoolDemo();
        demo.singleThreadExecutor();

    }

    public void cachedThreadPoolDemo() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

//            cachedThreadPool.execute(new Runnable() {
//
//                @Override
//                public void run() {
//                    System.out.println(index);
//                }
//            });

            // java 8
            cachedThreadPool.execute(() -> System.out.println(index));

        }
    }

    public void fixedThreadPoolDemo(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;

//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

            // java 8
            fixedThreadPool.execute(() -> {
                System.out.println(index);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void scheduledThreadPoolDemo() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);

        // java8
        scheduledThreadPool.schedule(() -> System.out.println("delay 3 secodes"), 3, TimeUnit.SECONDS);
    }

    public void singleThreadExecutor(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;

            singleThreadExecutor.execute(() -> {
                System.out.println(index);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
    }
}
