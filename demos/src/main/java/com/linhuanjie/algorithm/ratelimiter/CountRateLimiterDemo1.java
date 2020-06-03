package com.linhuanjie.algorithm.ratelimiter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountRateLimiterDemo1 {
    private static AtomicInteger count = new AtomicInteger(0);

    private static final int maxRequest = 5;

    public static void exec() {
        if (count.get() >= maxRequest) {
            System.out.println("请求用户过多，请稍后在试！" + System.currentTimeMillis() / 1000);
        } else {
            count.incrementAndGet();
            try {
                //处理核心逻辑
                TimeUnit.SECONDS.sleep(1);
                System.out.println("done -- " + System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.decrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                10,
                10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(300));

        for (int i = 0; i < 30; i++) {
            executor.execute(() -> exec());
        }
    }
}