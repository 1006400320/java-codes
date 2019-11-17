package com.linhuanjie.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019.11.11 22:23
 * @email: lhuanjie@qq.com
 */
public class InHertThreadTest {


    /*public static void main(String[] args) {
        InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
        //给主线程中的ThreadLocal赋值
        inheritableThreadLocal.set("main first value");
        //启动一个子线程，判断看有没有获取到主线程中的这个值
        System.out.println("main Thread First Print ThreadLocal Value:"+inheritableThreadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("尝试打印主线程中的ThreadLocal中的值:"+inheritableThreadLocal.get());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("third Thread :"+inheritableThreadLocal.get());
                    }
                }).start();
                //在子线程中设置ThreadLocal的值 后续要判断在主线程中看打印的是什么
                inheritableThreadLocal.set("son value");

                System.out.println("再次打印ThreadLocal中的值，可以得知获取的是子线程中的:"+inheritableThreadLocal.get());
                //睡眠10s在打印 看是不是main second value
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("睡眠10s 再次打印看是不是second value:"+inheritableThreadLocal.get());
            }
        }).start();
        //再次打印ThreadLocal中的值，用来判断是哪个线程赋予的值
        inheritableThreadLocal.set("main second value");
        System.out.println("main Thread print ThreadLocal Value:"+inheritableThreadLocal.get());
    }*/

    @Test
    public void main2() {
        final InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
        inheritableThreadLocal.set("hello");
        final InheritableThreadLocal inheritableThreadLocal1 = new InheritableThreadLocal();
//        inheritableThreadLocal1.set("hello1");
        final ThreadLocal threadLocal2 = new ThreadLocal();
        threadLocal2.set("hello 2");
        new Thread(new Runnable() {
            InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

            @Override
            public void run() {
                System.out.println(inheritableThreadLocal.get());
                System.out.println(inheritableThreadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();
    }


    public static void main(String[] args) {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        System.out.println(o);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
                System.out.println(inheritableThreadLocal.get());
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println(inheritableThreadLocal.get());
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            executorService.submit(runnable);
            TimeUnit.SECONDS.sleep(1);
            executorService.submit(runnable);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("========");
        System.out.println(inheritableThreadLocal.get());

    }

    static class Span {

        public String name;
        public int age;

        public Span(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Span{");
            sb.append("name='").append(name).append('\'');
            sb.append(", age=").append(age);
            sb.append('}');
            return sb.toString();
        }
    }
}
