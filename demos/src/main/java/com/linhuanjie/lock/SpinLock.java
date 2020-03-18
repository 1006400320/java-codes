package com.linhuanjie.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 简单的自旋锁
 * @CreateTime : 2020-1-13 11:00
 **/
public class SpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<Thread>();
    public void lock() {
        Thread current = Thread.currentThread();
        // 利用CAS
        while (!cas.compareAndSet(null, current)) {
            // DO nothing
        }
    }
    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }

}
