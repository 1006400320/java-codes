package com.linhuanjie.thread.multithread;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.quartz.spi.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2019.11.17 9:49
 **/
public class InheritableThreadLocalDemo {


    public static void main(String[] args) {
        final Integer size = 10;
//        final CountDownLatch countDownLatch = new CountDownLatch(size);
//        InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
        TransmittableThreadLocal ttl = new TransmittableThreadLocal();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(200));

//        List<Integer> list = new ArrayList<>();
        // 初始化数据
        for (int i = 0; i < size; i++) {
//            list.add(i);
            ttl.set(i);
//            executor.execute(new Worker(inheritableThreadLocal));
            executor.execute(new Worker(ttl));

        }

    }
}

class Worker implements Runnable {

//    private InheritableThreadLocal inheritableThreadLocal;
    private TransmittableThreadLocal ttl;

    public Worker(TransmittableThreadLocal ttl) {
        this.ttl = ttl;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.ttl.get();
        System.out.println("thread-name:" + Thread.currentThread().getName() + "get = " + this.ttl.get());
    }
}
