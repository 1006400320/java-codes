package com.linhuanjie.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020/4/8 18:54
 **/
public class GuavaCacheDemo {

    ListeningExecutorService backgroundRefreshPools =
            MoreExecutors.listeningDecorator(new ThreadPoolExecutor(3, 10,
                    0L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(10)));


    @Test
    public void testCacheLoader() throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                // 最大缓存数
                .maximumSize(1000)
                // 过期时间
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    // 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                    public String load(String name) throws Exception {
                        return "value_of_" + name;
                    }

                    @Override
                    // 刷新时，开启一个新线程异步刷新，老请求直接返回旧值，防止耗时过长
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        return backgroundRefreshPools.submit(() -> getDataFromDB(key));
                    }

                    private String getDataFromDB(String key) {
                        return "data from db";
                    }

                });

        String value = cache.get("张三");
        System.out.println("value = " + value);
    }

    @Test
    public void testCallableCache() throws ExecutionException {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(100).build();

        String resultVal = cache.get("cache", () -> {
            return "result";
        });

        System.out.println("resultVal = " + resultVal);

    }


}