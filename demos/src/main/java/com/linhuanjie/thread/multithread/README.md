### [Java并发协作控制](https://www.icourse163.org/learn/ECNU-1003718005?tid=1206788218#/learn/content?type=detail&id=1211641400&cid=1214440897)
 
#### Lock 锁
 - lock()
 - unlock()
 - trylock():尝试获取锁，如果可以获取到则锁住
 

#### Semaphore  信号
 - tryAcquire() 尝试获取
 - release() 释放
 
#### CountDownLatch  
 - await() 等待
 - countDown() 就绪                

#### CyclicBarrier
 - await() 等待

#### Phaser
 - arriveAndAwaitAdvance()

#### Exchanger 线程间交换数据
 - exchange()




