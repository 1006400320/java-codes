package com.linhuanjie.thread;


import com.linhuanjie.common.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2019.11.17 12:50
 **/
public class CallableDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue(200));
        List<Future<String>> resultList = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> result = executorService.submit(new CallableTaskWithResult(i));
            resultList.add(result);
        }

        System.out.println("================QAQ==================");

//        System.out.println("JsonUtils.toString(resultList) = " + JsonUtils.toString(resultList));
        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println(fs.get());     //打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
                executorService.shutdown();
            }
        }
    }


}

class CallableTaskWithResult implements Callable<String> {
    private int id;

    public CallableTaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "开始干活了QAQ...");
        return Thread.currentThread().getName() + "：call()方法被【" + id + "】次调用";
    }
}
