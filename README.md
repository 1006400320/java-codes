### 好记性不如烂笔头    --  鲁迅
![鲁迅.jpg](http://ww1.sinaimg.cn/large/005CzYvJgy1ge1php2eplj3073073t8s.jpg)
> 明日复明日，明日何其多

- [ ] JVM
    - [x] [类加载子系统](note/JVM/类加载子系统.md)
    - [x] [运行时数据区](note/JVM/运行时数据区.md)
    - [ ] [JVM常用参数](note/JVM/JVM常用参数.md)

- [ ] 网络
    - [ ] 三次挥手四次握手
    - [ ] http 和 rpc 的区别

- [ ] 算法
    - [ ] 令牌桶
    - [ ] 漏桶算法

- [ fail-fast和fail-safe的介绍和区别](https://blog.csdn.net/Kato_op/article/details/80356618)

漏桶算法
我们可以把发请求的动作比作成注水到桶中，我们处理请求的过程可以比喻为漏桶漏水。我们往桶中以任意速率流入水，以一定速率流出水。当水超过桶流量则丢弃，因为桶容量是不变的，保证了整体的速率。如果想要实现这个算法的话也很简单，准备一个队列用来保存请求，然后我们定期从队列中拿请求来执行就好了。


漏桶算法
令牌桶算法
令牌桶算法也比较简单。和漏桶算法算法一样，我们的主角还是桶（这限流算法和桶过不去啊）。不过现在桶里装的是令牌了，请求在被处理之前需要拿到一个令牌，请求处理完毕之后将这个令牌丢弃（删除）。我们根据限流大小，按照一定的速率往桶里添加令牌。


令牌桶算法
具体基于以上算法如何实现，Guava提供了RateLimiter工具类基于基于令牌桶算法：

RateLimiter rateLimiter = RateLimiter.create(5);
以上代码表示一秒钟只允许处理五个并发请求，以上方式只能用在单应用的请求限流，不能进行全局限流；这个时候就需要分布式限流，可以基于redis+lua来实现；



- [ ] JavaSE
  - [ ] collection
    -  [ ] [fail-fast&fail-safe]()
- [x] [markdown语法](note/tools/Markdown.md)

- [x] [注解](note/java/annotation.md#注解)

  - [ ]  [常用注解_较完整](https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/framework/spring/spring-annotations.md)
  - [ ]  [常用Spring注解](note/spring/SpringAnnotation.md)
  - [ ]  [SpringBoot注解](note/spring/SpringBoot注解.md)
  - [ ]  [Spring@Transactional](note/spring/Spring@Transactional.md)


- [ ] spring
    - [ ] [Bean生命周期流程图](note/spring/Bean生命周期流程图.md)
    - [x] [RestTemplate](demos/src/main/java/com/linhuanjie/spring/RestTemplateDemo.java#L7-L26)
    
    - [ ] springcloud

- [x] mq
  - [x] [ActiveMQ](note/mq/ActiveMQ.md#mqmessage-queue应用场景)
  - [ ] [rocketmq](note/mq/RocketMQ-01.md)
    - [ ] [RocketMQ-源码分析](note/mq/RocketMQ-03.md#2-源码分析)
  - [x] [rabbitmq](note/mq/RabbitMQ.md)
  - [ ] kafka

- [ ] 数据库
  - [ ] [Mysql实战45讲](note/MySQL45讲/README.md)
  - [ ] mybatis-plus

- [x] [Guava](demos/src/main/java/com/linhuanjie/guava/README.md)
  - [x] Cache
  - [ ] 集合 Maps.newHashMapWithExepectSize

- [ ] 缓存
    - [ ] ConcurrentHashMap
    - [ ] [SimpleCache](base/src/main/java/com/linhuanjie/common/utils/SimpleCache.java#L10-L118)

- [ ] 设计模式
  - [x] [六大设计原则](note/java/设计模式/六大设计原则.md)
  - [x] [单例模式](note/java/设计模式/单例模式.md)
  - [x] [简单工厂模式](note/java/设计模式/简单工厂模式.md)
  - [x] [工厂方法模式](note/java/设计模式/工厂方法模式.md)
  - [x] [抽象工厂模式](note/java/设计模式/抽象工厂模式.md)
  - [x] [工厂模式总结](note/java/设计模式/工厂模式总结.md)
  - [x] [原型模式](note/java/设计模式/原型模式.md)
  - [x] [建造者模式](note/java/设计模式/建造者模式.md)
  - [ ] [适配器模式](note/java/设计模式/适配器模式.md)
  - [ ] [桥接模式](note/java/设计模式/桥接模式.md)
  - [ ] [装饰模式](note/设计模式/装饰模式.md)
  - [ ] [组合模式]()
  - [ ] [观察者模式](note/设计模式/观察者模式.md)
  - [ ] [外观模式]()
  - [ ] [迭代器模式]()
  - [ ] [解释器模式]()
  - [ ] [责任链模式]()
  - [ ] [代理模式](note/设计模式/代理模式.md)
  - [ ] [调停者模式]()
  - [ ] [享元模式]()
  - [ ] [模板方法模式]()
  - [ ] [命令模式]()
  - [ ] [状态模式]()
  - [ ] [策略模式]()
  - [ ] [访问者模式]()
  - [ ] [备忘录模式]()


- [ ] 并发
    - [ ] CountDownLatch
    - [ ] Lock
    - [ ] Condition
    - [ ] CyclicBarrier


- [ ] Elasticsearch

- [ ] XXL-JOB

- [ ] Arthas

- [ ] tools
    - [ ] [hutools](https://hutool.cn/docs/#/)
        - [ ] [树结构工具-TreeUtil](https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/%E6%A0%91%E7%BB%93%E6%9E%84/%E6%A0%91%E7%BB%93%E6%9E%84%E5%B7%A5%E5%85%B7-TreeUtil)
    - [ ] [mapstruct](demos/src/main/java/com/linhuanjie/mapstruct/MapStructTest.java)
    - [x] [lombok](note/tools/lombok.md)
    - [ ] [布隆过滤器](note/tools/布隆过滤器.md)
    - [x] [Markdown](note/tools/Markdown.md)
    - [ ] [Windows子系统](note/tools/Windows子系统.md)
    - [ ] [tomcat调优](note/tools/tomcat调优.md)
    - [x] [IDEA](note/tools/IDEA.md)
    - [x] [jdk8升级](note/tools/升级jdk8.md)
    - [ ] [maven](note/tools/maven.md)
    - [ ] [gradle](note/tools/gradle.md)
    - [ ] git
        - [ ] [Github搜索技巧](note/tools/git/Github搜索技巧.md)
        - [ ] [Git命令](note/tools/git/Git命令.md)
        - [ ] [Git常用](note/tools/git/Git常用.md)

    - [ ] [JAVA线上故障排查全套路](https://fredal.xin/java-error-check)

### 心流
Question：为什么集中精力做一件事情的时候容易“忘我”？（比如编程，玩游戏）  
Answer：“忘我” 其实是一种 **心流**的心理状态
> 心流（英语：Mental flow）在心理学中是指一种人们在专注进行某行为时所表现的心理状态。如艺术家在创作时所表现的心理状态。  
> 通常在此状态时，**不愿被打扰**，也称抗拒中断。是一种将个人精神力完全投注在某种活动上的感觉。心流产生的同时会有高度的兴奋及充实感。  
> 米哈里·契克森米哈赖认为，使心流发生的活动有多样性。  
> 来源：百度百科

优点：
- 全神贯注
- 不愿被打扰
- 忽略时间，废寝忘食

如何进入心流状态：
1. 清晰的目标
2. 及时的反馈。
3. 能力相当的任务。

