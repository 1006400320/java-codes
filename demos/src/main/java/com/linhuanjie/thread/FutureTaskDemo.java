package com.linhuanjie.thread;

import java.util.concurrent.*;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-05-22 20:30
 * @email: lhuanjie@qq.com
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        // 要执行的任务
        MyCallable callable1 = new MyCallable(1000);
        MyCallable callable2 = new MyCallable(2000);

        // 将Callable写的任务封装到一个由执行者调度的FutureTask对象
        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        // 创建线程池并返回ExecutorService实例
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 执行任务
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true) {
            try {
                //  两个任务都完成
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("Done");
                    // 关闭线程池和服务
                    executor.shutdown();
                    return;
                }

                // 任务1没有完成，会等待，直到任务完成
                if (!futureTask1.isDone()) {
                    System.out.println("FutureTask1 output=" + futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if (s != null) {
                    System.out.println("FutureTask2 output=" + s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                //do nothing
            }
        }
    }
}


class MyCallable implements Callable<String> {
    private long waitTime;

    public MyCallable(int timeInMillis) {
        this.waitTime = timeInMillis;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }

}