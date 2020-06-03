package com.linhuanjie.algorithm.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * guava的限流工具RateLimiter使用
 * https://www.cnblogs.com/leeego-123/p/11492822.html
 *
 * @author : linhuanjie
 * @createtime : 2020/6/1 16:43
 **/
public class GuavaRateLimiterDemo {


    public static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<String, RateLimiter>();

    //初始化限流工具RateLimiter
    static {
        createResourceRateLimiter("order", 50);
    }

    public static void createResourceRateLimiter(String resource, double qps) {
        if (resourceRateLimiter.contains(resource)) {
            resourceRateLimiter.get(resource).setRate(qps);
        } else {
            //创建限流工具，每秒发出50个令牌指令
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimiter.putIfAbsent(resource, rateLimiter);

        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                //如果获得令牌指令，则执行业务逻辑
                if (resourceRateLimiter.get("order").tryAcquire(10, TimeUnit.MICROSECONDS)) {
                    System.out.println("执行业务逻辑");
                } else {
                    System.out.println("限流");
                }
            }).start();
        }

    }




  /*  public static final int REQUEST_COUNT = 2;

    *//*** set the number of requests per second *//*
    private static final RateLimiter rateLimiter = RateLimiter.create(REQUEST_COUNT);

    public static void main(String[] args) {


      *//*  ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20,
                20,
                10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(300));

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> exec());
        }*//*

        String a = "a";
        String b = "b";
        System.out.println(String.format("已创建限流组件-[identify=%s, permitsPerSnd=%s].",  a , b));
    }


    public static void exec() {
        if (!rateLimiter.tryAcquire()) {
            System.out.println("被限流了。。。");
            return;
        }
        System.out.println("成功请求。。。");
    }
*/
}
