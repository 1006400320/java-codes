package com.linhuanjie.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列  BlockingQueue 的使用
 *
 * 阻塞队列：最常见的业务场景就是生产者不断生产任务放进阻塞队列中，消费者不断从阻塞队列中获取任务；
 *          当阻塞队列中填满数据时，所有生产者端的线程自动阻塞，当阻塞队列中数据为空时，所有消费端的线程自动阻塞。
 *          这些操作BlockingQueue都已经包办了，不用我们程序员去操心了。
 */
public class BlockQueue {

    public static void main(String[] args){
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(2); //定长为2的阻塞队列
        //ExecutorService：真正的线程池接口
        ExecutorService service = Executors.newCachedThreadPool();  //缓存线程池
        //创建3个生产者：
        ProducerDemo p1 = new ProducerDemo("车鉴定web端",queue);
        ProducerDemo p2 = new ProducerDemo("车鉴定APP端",queue);
        ProducerDemo p3 = new ProducerDemo("车鉴定接口端",queue);
        ProducerDemo p4 = new ProducerDemo("车鉴定M栈",queue);
        //创建三个消费者：
        ConsumerDemo c1 = new ConsumerDemo("ToyotaYQ_001",queue);
        ConsumerDemo c2 = new ConsumerDemo("ToyotaYQ_002",queue);
        ConsumerDemo c3 = new ConsumerDemo("ToyotaYQ_003",queue);

        //启动线程
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(p4);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);

    }
}

/**
 * 生产者
 */
class ProducerDemo implements Runnable {
    private String producerName;
    private BlockingQueue queue;//阻塞队列
    private Random r = new Random();

    //构造函数,传入生产者名称和操作的阻塞队列
    public ProducerDemo(String producerName,BlockingQueue queue) {
        this.producerName = producerName;
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                int task = r.nextInt(100);  //产生随机数
                System.out.println(producerName + "开始生产任务：" + task);
                queue.put(task);  //生产者向队列中放入一个随机数
                Thread.sleep(5000);  //减缓生产者生产的速度，如果队列为空，消费者就会阻塞不会进行消费直到有数据被生产出来
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerDemo implements Runnable{
    private String consumerName;
    private BlockingQueue queue;//阻塞队列

    //构造函数,传入消费者名称和操作的阻塞队列
    public ConsumerDemo(String consumerName,BlockingQueue queue) {
        this.consumerName = consumerName;
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println(consumerName + "开始消费任务---" + queue.take());//消费者从阻塞队列中消费一个随机数
                //Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
