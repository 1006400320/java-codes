package com.linhuanjie.algorithm.ratelimiter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用 Semaphore 实现限流
 *
 * [理解 Semaphore 及其用法详解](https://blog.csdn.net/u013851082/article/details/70208246)
 */
public class SemaphoreRateLimiterDemo {
    private static Semaphore semphore = new Semaphore(5);
    public static void exec() {
        if(semphore.getQueueLength()>10){
            System.out.println("当前等待排队的任务数大于10，请稍候再试...");
        }
        try {
            // 获取一个许可
            semphore.acquire();
            // 处理核心逻辑
            TimeUnit.SECONDS.sleep(1);
            System.out.println("down -- " + System.currentTimeMillis() / 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放一个许可
            semphore.release();
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20,
                20,
                10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(300));

        for (int i = 0; i < 50; i++) {
            executor.execute(() -> exec());
        }
    }
}