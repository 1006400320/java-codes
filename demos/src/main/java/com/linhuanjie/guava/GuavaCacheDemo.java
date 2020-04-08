package com.linhuanjie.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.concurrent.TimeUnit;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020/4/8 18:54
 **/
public class GuavaCacheDemo {


    public static void main(String[] args) {
//        LoadingCache<Integer, String> vaule = CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
//                .build(
//                        new CacheLoader<Key, Graph>() {
//                            public Graph load(Key key) {
//                                return createExpensiveGraph(key);
//                            }
//                        });
    }

}
