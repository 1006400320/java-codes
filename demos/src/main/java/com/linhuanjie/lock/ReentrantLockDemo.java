package com.linhuanjie.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020-1-9 11:38
 **/
public class ReentrantLockDemo {
    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo(i)).start();
        }

    }

    static class ThreadDemo implements Runnable {
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    System.out.println("获得锁的线程：" + id);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
