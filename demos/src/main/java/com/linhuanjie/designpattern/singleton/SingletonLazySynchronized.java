package com.linhuanjie.designpattern.singleton;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 单例模式（synchronized） ,直接加 synchronized 会影响系统性能
 * @CreateTime : 2020/4/16 14:20
 **/
public class SingletonLazySynchronized {
    private static SingletonLazySynchronized singleton = null;

    private SingletonLazySynchronized() {
    }

    public static synchronized SingletonLazySynchronized getInstance() {
        if (singleton == null) {
            System.out.println("啊，我被实例化了~");
            singleton = new SingletonLazySynchronized();
        }

        return singleton;
    }


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                50,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(300));

        for (int i = 0; i < 100; i++) {

            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    SingletonLazySynchronized instance1 = SingletonLazySynchronized.getInstance();
                    System.out.println("instance" + finalI + "，线程【" + Thread.currentThread().getName() + "】:" + instance1);

                }
            });
        }

    }
}
