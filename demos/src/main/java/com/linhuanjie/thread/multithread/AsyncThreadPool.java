package com.linhuanjie.thread.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 异步线程池 , 其他地方的线程池均由其统一管理
 * @CreateTime : 2019.11.17 12:37
 **/

@Component
public class AsyncThreadPool {
    private static Logger logger = LoggerFactory.getLogger(AsyncThreadPool.class);

    protected int coreSize = 10;
    protected int poolSize = 10;
    private ExecutorService executorService;

    public AsyncThreadPool() {
    }

    public AsyncThreadPool(int poolSize) {
        this.poolSize = poolSize;
    }

    public ExecutorService getExecutorService() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            //子线程继承主线程的上下文
            RequestContextHolder.setRequestAttributes(requestAttributes, true);
        } catch (Exception e) {
            //	e.printStackTrace();
            logger.error("异步线程报错：" + e.getMessage());
        }
        return executorService;
    }

    @PostConstruct
    public void init() {
//        executorService = Executors.newFixedThreadPool(poolSize);
        executorService = new ThreadPoolExecutor(coreSize, poolSize, 0L,
                TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(200));
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }
}
