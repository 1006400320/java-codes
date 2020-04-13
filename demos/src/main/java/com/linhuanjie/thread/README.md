### 内核级线程（KLT）和用户级线程（ULT）
#### 内核级线程（Kemel-Level Threads, KLT 也有叫做内核支持的线程) 
- 线程管理的所有工作（创建和撤销）由操作系统内核完成
- 操作系统内核提供一个应用程序设计接口API，供开发者使用KLT  
##### 纯内核级线程特点：
1. 进程中的一个线程被阻塞，内核能调度同一进程的其他线程（就绪态）占有处理器运行
2. 多处理器环境中，内核能同时调度同一进程的多线程，将这些线程映射到不同的处理器核心上，提高进程的执行效率。
3. 应用程序线程在用户态运行，线程调度和管理在内核实现。线程调度时，控制权从一个线程改变到另一线程，需要模式切换，系统开销较大。

#### 用户级线程（User-Level Threads ULT）
- 用户空间运行线程库，任何应用程序都可以通过使用线程库被设计成多线程程序。线程库是用于用户级线程管理的一个例程包，它提供多线程应用程序的开发和运行支撑环境，包含：用于创建和销毁线程的代码、在线程间传递数据和消息的代码、调度线程执行的代码以及保存和恢复线程上下文的代码。
- 所以线程的创建，消息传递，调度，保存/恢复上下文都有线程库来完成。内核感知不到多线程的存在。内核继续以进程为调度单位，并且给该进程指定一个执行状态（就绪、运行、阻塞等）。

##### 纯用户级线程的特点：
1. 线程切换不需要内核模式，能节省模式切换开销和内核资源。
2. 允许进程按照特定的需要选择不同的调度算法来调度线程。调度算法需要自己实现。
3. 由于其不需要内核进行支持，所以可以跨OS运行。
4. 不能利用多核处理器有点，OS调度进程，每个进程仅有一个ULT能执行
5. 一个ULT阻塞，将导致整个进程的阻塞。


### 线程的生命周期
- 新建状态（New）
- 就绪状态（Runnable）
- 运行状态（Running）
- 阻塞状态（Blocked）
- 死亡状态
![线程生命周期.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gds5hd1ggwj30uh0mcjxy.jpg)
### new Thread的弊端如下：

1. 每次new Thread新建对象性能差。 
2. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。 
3. 缺乏更多功能，如定时执行、定期执行、线程中断。

### 线程池的好处在于：

1. 重用存在的线程，减少对象创建、消亡的开销，性能佳。 
2. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。 
3. 提供定时执行、定期执行、单线程、并发数控制等功能。



### Java通过Executors提供四种线程池(建议手动创建线程池 ThreadPoolExecutor)

- **newCachedThreadPool** 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。  
- **newFixedThreadPool** 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
- **newScheduledThreadPool** 创建一个定长线程池，支持定时及周期性任务执行。
- **newSingleThreadExecutor** 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

### ThreadPoolExecutor 创建线程池
> 原文：[线程池ThreadPoolExecutor源码分析](https://www.yuque.com/docs/share/8a3b5182-7330-459d-805f-e32b7f009a14?#)
```java
ThreadPoolExecutor executorService = new ThreadPoolExecutor(coreSize, poolSize, 0L,TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(200));
```
####  线程池内定义了五种运行状态，
 分别是：
- RUNNING：处于RUNNING状态的线程池能接受新提交的任务，并且也能处理阻塞队列中的任务。
- SHUTDOWN：处于SHUTDOWN状态的线程池，不再接受新提交的任务，但却可以继续处理阻塞队列中已保存的任务。
- STOP：处于STOP状态，线程池不能接受新任务，也不处理队列中的任务，正在处理任务的线程会被中断。
- TIDYING：如果所有的任务都已终止，workerCount 为0，线程池进入该状态后会调用terminated（）方法进入TERMINATED状态。
- TERMINATED：TIDYING状态下，线程池执行完terminated钩子方法后进入此状态，此时线程池已完全终止。

#### ThreadPoolExecutor 构造方法:
- corePoolSize：核心线程数量，当有新任务在execute() 方法提交时，会根据以下进行判断：
  1. 如果线程池内线程数量少于 corePoolSize ，则创建新建线程处理提交的任务，即使是线程池内其他线程时空闲状态；
  2. 如果线程池内线程数量大于等于 corePoolSize 且小于 maximumPoolSize, 线程池不会再继续创建新的线程，只有当workQueue满时才创建新的线程去处理任务；
  3. 如果设置的corePoolSize 和 maximumPoolSize相同，则创建的线程池的大小是固定的，这时如果有新任务提交，若workQueue未满，则将请求放入workQueue中，等待有空闲的线程去从workQueue中取任务并处理；
  4. 如果workQueue已经满了，则线程池创建新的线程处理提交的任务，直至运行的线程数量大于等于maximumPoolSize，则通过handler所指定的策略来处理任务；
- maximumPoolSize：线程池中允许存活的最大线程数量。
- keepAliveTime：线程池维护线程所允许的空闲时间。当线程池中存活的线程数量大于 corePoolSize 时，此时如果没有新任务提交，线程池内的线程会等待，直至等待时间超过keepAliveTime，线程池会销毁大于corePoolSize 数量的线程。
- unit： 时间单位
- workQueue：等待执行的任务所存放的阻塞队列。可选用多种阻塞队列：
    > 线程池的线程数大于或等于基本大小(poolSize >= corePoolSize) 且任务队列未满时，  
    > 就将新提交的任务提交到阻塞队列排队，等候处理workQueue.offer(command)；
   - LinkedBlockingQueue：基于链表的阻塞队列。一般选用此队列作为无界队列，默认情况下，队列容量是 Integer 的最大值。在不指定队列容量的情况下，线程池中能够创建的最大线程数就是corePoolSize。当线程池中所有的线程都是RUNNING 状态时，新提交的任务会放入等待队列中。
    - ArrayBlockingQueue：基于数组的阻塞队列。初始化该队列时必须传入初始容量，使用该队列可以使用线程池的最大线程数量maximumPoolSize。
    - SynchronousQueue：
    - PriorityBlockingQueue：
- threadFactory：默认使用Executors.defaultThreadFactory() 方法创建新线程。该方法创建线程时，新线程具有相同的 NORM_PRIOP == 5 优先级并且该线程非守护线程，同时也设置了线程的名称。
- handler：线程池的拒绝策略。如果阻塞队列满了并且没有空闲的线程，此时继续提交任务，线程池就需要采取以下4种策略处理该任务。
    - AbortPolicy：默认策略，直接抛出异常；
    - CallerRunsPolicy：用调用者所在的线程来执行任务；
    - DiscardOldestPolicy：丢弃阻塞队列中靠前的任务，并执行当前任务；
    - DiscardPolicy：直接丢弃任务；
    - 我们也可以实现自己的拒绝策略，例如记录日志等等，实现RejectedExecutionHandler 接口即可。
- allowCoreThreadTimeout：核心线程超时控制标志位，用于标识 keepAliveTime 是否作用于核心线程上。



#### execute和submit的区别


参考文章： [内核级线程（KLT）和用户级线程（ULT）](https://blog.csdn.net/vinter_he/article/details/79788743)



