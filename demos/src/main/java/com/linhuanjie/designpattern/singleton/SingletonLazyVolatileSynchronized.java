package com.linhuanjie.designpattern.singleton;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 单例模式（双重检查）
 * @CreateTime : 2020/4/16 14:20
 **/
public class SingletonLazyVolatileSynchronized {
    // volatile可以确保当singleton被初始化后，多线程才可以正确处理
    // 被volatile修饰的变量的值，将不会被本地线程缓存
    // 对该变量读写都是直接操作共享内存，确保多个线程能正确的处理该变量。
    private static volatile SingletonLazyVolatileSynchronized singleton = null;

    private SingletonLazyVolatileSynchronized() {
    }

    public static SingletonLazyVolatileSynchronized getInstance() {
        if (singleton == null) {
            synchronized (SingletonLazyVolatileSynchronized.class) {
                if (singleton == null) {
                    System.out.println("啊，我被实例化了~");
                    singleton = new SingletonLazyVolatileSynchronized();
                }
            }
        }

        return singleton;
    }


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(300));

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.execute(() -> {
                SingletonLazyVolatileSynchronized instance1 = SingletonLazyVolatileSynchronized.getInstance();
                System.out.println("instance" + finalI + "，线程【" + Thread.currentThread().getName() + "】:" + instance1);
            });
        }

    }
}
