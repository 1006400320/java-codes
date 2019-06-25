package com.linhuanjie.queue;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 并发队列 ConcurrentLinkedQueue 的使用
 *
 * 并发队列：最常见的业务场景就是多个线程共享同一个队列中的所有资源，就拿我们公司的业务场景来说，
 * 当用户通过多个渠道下单后，然后就会有多个不同的客户端通道同时去获取订单并处理订单，
 * 为了加快订单处理速度我们使用并发队列来充当任务源头，为了加快处理订单速度，结合多线程并发来满足需求。
 */

public class ConcurrentQueue {

    public static void main(String[] args) {
        ToyotaYQ yq = new ToyotaYQ();
        new Thread(yq, "ToyotaYQ_001").start();
        new Thread(yq, "ToyotaYQ_002").start();
        new Thread(yq, "ToyotaYQ_003").start();
    }

}

/**
 * 任务来源
 */
class MQ {
    private static Queue<String> queue = null;    //并发队列(线程安全)

    /**
     * 初始化并发队列
     */
    public static Queue<String> initQueue() {
        if (queue == null) {
            queue = new ConcurrentLinkedQueue<String>();
        }
        String tasklist = "JF1GH78F18G036149,JF1SH95F6AG110830,JF1SJ94D7DG010387,JF1SH92F9CG269249,JF1SH92F5BG215090,JF1SH92F5BG222556,JF1SH92F4CG279994,JF1BR96D7CG114298,JF1BR96D0BG078632,JF1SH95F9AG094011,JF1SH98FXAG186997,JF1BM92D8BG022510,JF1BM92DXAG013855,JF1BM94D8EG036618";
        String[] split = tasklist.split(",");
        List<String> task = Arrays.asList(split);    //数组转集合
        queue.addAll(task);        //按照集合中元素的顺序将集合中全部元素放进队列

        return queue;
    }
}

/**
 * 制单客户端
 */
class ToyotaYQ implements Runnable {

    private static final Object lock = new Object();
    private static Queue<String> queueYQ = MQ.initQueue();

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {    //尽量减小锁的粒度和范围
                String thisVIN = queueYQ.poll();
                if (thisVIN == null) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "成功制单：" + thisVIN + "。剩余：" + queueYQ.size() + "个任务");
            }
        }
    }
}
