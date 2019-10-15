package com.linhuanjie.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.awt.*;
import java.util.concurrent.*;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-05-22 21:30
 * @email: lhuanjie@qq.com
 */
public class MyThreadPool {
    /**
     * 基本参数
     */
    static int corePoolSize = 10;

    static int maximumPoolSizeSize = 100;

    static long keepAliveTime = 1;

    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);

    public static void main(String[] args) throws InterruptedException {
/*

        // 使用默认饱和策略创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSizeSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue,
                // JDK1.8后此方法作废
                new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build());
        // 向线程池中添加任务
        executor.execute(() -> System.out.println("ok"));
*/


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));
        threadPoolExecutor.execute(new TestThread());
        threadPoolExecutor.execute(new TestThread());
        threadPoolExecutor.execute(new TestThread());
        threadPoolExecutor.execute(new TestThread());

        threadPoolExecutor.execute(new TestThread());
        threadPoolExecutor.execute(new TestThread());


//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("MyExample-schedule-pool-%d").daemon(true).build());
//
//
//        ConcurrentLinkedQueue linkedQueue = new ConcurrentLinkedQueue();
//        linkedQueue.add("a");
//        linkedQueue.add("b");
//        linkedQueue.add("c");
//
//        System.out.println(linkedQueue.size());
//        System.out.println(linkedQueue.peek());
//        System.out.println(linkedQueue.poll());
//        System.out.println(linkedQueue.size());
//
//        System.out.println("=======================");
//
//        BlockingQueue blockingQueue = new LinkedBlockingQueue(3);
//        blockingQueue.offer("a");
//        blockingQueue.offer("b");
//        blockingQueue.offer("c");
//        System.out.println(blockingQueue.poll());
//        blockingQueue.put("d");
//
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());


    }

}

//public class MyThreadPool {
//    public static void main(String[] args){
//        //创建等待队列
//        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
//        //创建线程池，池中保存的线程数为3，允许的最大线程数为5
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50, TimeUnit.MILLISECONDS,bqueue);
//        //创建七个任务
//        for (int i = 0; i < 7; i++) {
//            //每个任务会在一个线程上执行
//            pool.execute(new MyThreadDemo());
//        }
//
//        //关闭线程池
//        pool.shutdown();
//    }
//}
//
//
//class MyThreadDemo implements Runnable{
//    @Override
//    public void run(){
//        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
//        try{
//            Thread.sleep(100);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//    }
//}

class TestThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
