### 好记性不如烂笔头    --  鲁迅
![鲁迅.jpg](http://ww1.sinaimg.cn/large/005CzYvJgy1ge1php2eplj3073073t8s.jpg)
> 明日复明日，明日何其多

- [ ] JVM
    - [x] [类加载子系统](note/JVM/类加载子系统.md)
    - [x] [运行时数据区](note/JVM/运行时数据区.md)
    - [ ] [JVM常用参数](note/JVM/JVM常用参数.md)

    - [ ] jstack(查看线程)、jmap(查看内存)和jstat(性能分析)
    - [ ] [JIT(just in time compilation)即时编译]
        - [什么是JIT](https://www.cnblogs.com/dzhou/p/9549839.html)
        - [深入浅出 JIT 编译器 ](https://www.ibm.com/developerworks/cn/java/j-lo-just-in-time/index.html)
    - [ ][Java问题定位之Java线程堆栈分析](https://blog.csdn.net/weiweicao0429/article/details/53185999)
    - [ ][JAVA线上故障排查全套路](https://fredal.xin/java-error-check) 

- [ ] 网络
    - [ ] 三次挥手四次握手
    - [ ] http 和 rpc 的区别

- [ ] 算法
    - [ ] [rateLimiter限流算法](note/algorithm/rateLimiter限流算法.md)

- [ fail-fast和fail-safe的介绍和区别](https://blog.csdn.net/Kato_op/article/details/80356618)

- [ ] [linux](note/linux.md)


- [ ] JavaSE
  - [ ] collection
    - [ ] [fail-fast&fail-safe]()
    - [ ] [HASHMAP的死循环](https://coolshell.cn/articles/9606.html/comment-page-1#comments) 
- [x] [markdown语法](note/tools/Markdown.md)

- [x] [注解](note/java/annotation.md#注解)

  - [ ]  [常用注解_较完整](https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/framework/spring/spring-annotations.md)
  - [ ]  [常用Spring注解](note/spring/SpringAnnotation.md)
  - [ ]  [SpringBoot注解](note/spring/SpringBoot注解.md)
  - [ ]  [Spring@Transactional](note/spring/Spring@Transactional.md)


- [ ] spring
    - [ ] [Bean生命周期流程图](note/spring/Bean生命周期流程图.md)
    - [x] [RestTemplate](demos/src/main/java/com/linhuanjie/spring/RestTemplateDemo.java#L7-L26)
    - [ ] spring security
        - [芋道 Spring Boot 安全框架 Spring Security 入门](http://www.iocoder.cn/Spring-Boot/Spring-Security)
        - [Spring Security PasswordEncoder 密码校验和密码加密流程](https://www.jianshu.com/p/922963106729)
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
  - [x] 集合 Maps.newHashMapWithExepectSize

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
  - [x] [适配器模式](note/java/设计模式/适配器模式.md)
  - [x] [桥接模式](note/java/设计模式/桥接模式.md)
  - [x] [装饰模式](note/设计模式/装饰模式.md)
  - [ ] [组合模式]()
  - [x] [观察者模式](note/设计模式/观察者模式.md)
  - [ ] [外观模式]()
  - [ ] [迭代器模式]()
  - [ ] [解释器模式]()
  - [ ] [责任链模式]()
  - [x] [代理模式](note/设计模式/代理模式.md)
  - [ ] [调停者模式]()
  - [ ] [享元模式]()
  - [ ] [模板方法模式]()
  - [ ] [命令模式]()
  - [ ] [状态模式]()
  - [ ] [策略模式]()
  - [ ] [访问者模式]()
  - [ ] [备忘录模式]()

- [ ] [thread](demos/src/main/java/com/linhuanjie/thread/README.md)

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
    - [ ] dubbo(C:\IDEA\IdeaProjects\springboot-integration-examples)
    
    
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

