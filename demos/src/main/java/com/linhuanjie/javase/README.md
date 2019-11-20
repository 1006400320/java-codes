### volatile

 - [ ] [Java中Volatile关键字详解](https://www.cnblogs.com/zhengbin/p/5654805.html)

#### Java 内存模型中的可见性、原子性和有序性
- 可见性：可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。
- 原子性：原子是世界上的最小单位，具有不可分割性。
- 有序性：Java 语言提供了 volatile 和 synchronized 两个关键字来保证线程之间操作的有序性，  
    - volatile 是因为其本身包含“禁止指令重排序”的语义，   
    - synchronized 是由“一个变量在同一个时刻只允许一条线程对其进行 lock 操作”这条规则获得的，此规则决定了持有同一个对象锁的两个同步块只能串行执行。


#### Volatile原理
　　Java语言提供了一种稍弱的同步机制，即volatile变量，用来确保将变量的更新操作通知到其他线程。当把变量声明为volatile类型后，编译器与运行时都会注意到这个变量是**共享**的，**因此不会将该变量上的操作与其他内存操作一起重排序**。volatile变量不会被缓存在寄存器或者对其他处理器不可见的地方，因此在读取volatile类型的变量时总会返回最新写入的值。

　　在访问volatile变量时不会执行加锁操作，因此也就不会使执行线程阻塞，因此volatile变量是一种比sychronized关键字更轻量级的同步机制。
        ![普通变量与volatile变量](./imgs/普通变量与volatile变量.png)
        当对非 volatile 变量进行读写的时候，每个线程先从内存拷贝变量到CPU缓存中。如果计算机有多个CPU，每个线程可能在不同的CPU上被处理，这意味着每个线程可以拷贝到不同的 CPU cache 中。  
 
　　**而声明变量是 volatile 的，JVM 保证了每次读变量都从内存中读，跳过 CPU cache 这一步。**
#### Volatile特性
　　1. 保证此变量对所有的线程的可见性，这里的“可见性”，如本文开头所述，当一个线程修改了这个变量的值，volatile 保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。但普通变量做不到这点，普通变量的值在线程间传递均需要通过主内存来完成。  
　　2. 禁止指令重排序优化。有volatile修饰的变量，赋值后多执行了一个“load addl $0x0, (%esp)”操作，这个操作相当于一个内存屏障（指令重排序时不能把后面的指令重排序到内存屏障之前的位置），只有一个CPU访问内存时，并不需要内存屏障；（什么是指令重排序：是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理）。

#### Volatile 性能
　　volatile 的**读性能消耗与普通变量几乎相同，但是写操作稍慢**，因为它需要在本地代码中插入许多内存屏障指令来保证处理器不发生乱序执行。

### final  
原文：[浅析Java中的final关键字](https://www.cnblogs.com/dolphin0520/p/3736238.html)
####一.final关键字的基本用法
1.修饰类
当用final修饰一个类时，表明这个类不能被**继承**。  
也就是说，如果一个类你永远不会让他被继承，就可以用final进行修饰。  
final类中的成员变量可以根据需要设为final，但是要**注意final类中的所有成员方法都会被隐式地指定为final方法**。


2.修饰方法  
下面这段话摘自《Java编程思想》第四版第143页：  
>“使用final方法的原因有两个。第一个原因是把方法锁定，以防任何继承类修改它的含义；第二个原因是效率。在早期的Java实现版本中，会将final方法转为内嵌调用。但是如果方法过于庞大，可能看不到内嵌调用带来的任何性能提升。在最近的Java版本中，不需要使用final方法进行这些优化了。“

**因此，如果只有在想明确禁止 该方法在子类中被覆盖的情况下才将方法设置为final的。**

3.修饰变量  
修饰变量是final用得最多的地方，也是本文接下来要重点阐述的内容。  
首先了解一下final变量的基本语法：  
- 如果是**基本数据类型**的变量，则其数值一旦在**初始化之后便不能更改**；
- 如果是**引用类型**的变量，则在对其初始化之后便不能再让其指向另一个对象。









参考文章：[Java中Volatile关键字详解](https://www.cnblogs.com/zhengbin/p/5654805.html)



