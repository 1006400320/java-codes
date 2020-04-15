

# @Transactional注解
@Transactional 是声明式事务管理 编程中使用的注解
> 将标签放置在需要进行事务管理的方法上，而不是放在所有接口实现类上。  
只读的接口就不需要事务管理，由于配置了@Transactional就需要AOP拦截及事务的处理，**可能影响系统性能**。

##  添加位置
1. 接口实现类或接口实现方法**上**，而不是接口类中。
2. 访问权限：public 的方法才起作用。@Transactional 注解应该只被应用到 public 方法上，这是由 Spring AOP 的本质决定的。
3. 错误使用：
   1. 接口中A、B两个方法，A无@Transactional标签，B有，上层通过A间接调用B，此时事务不生效。
   2. 接口中异常（运行时异常）被捕获而没有被抛出。  
        spring 只有在抛出的异常为运行时 unchecked 异常时才回滚该事务，  
        异常为RuntimeException 的子类(Errors也会导致事务回滚)，  
        而抛出 checked 异常则不会导致事务回滚 。可通过 @Transactional rollbackFor进行配置。
   3. **多线程下事务管理因为线程不属于 spring 托管**，故线程不能够默认使用 spring 的事务,  
   也不能获取spring 注入的 bean 。  
  在被 spring 声明式事务管理的方法内开启多线程，多线程内的方法不被事务控制。  
  一个使用了@Transactional 的方法，如果方法内包含多线程的使用，方法内部出现异常，  
  不会回滚线程中调用方法的事务


## 实现原理：
1. 事务开始时，通过AOP机制，生成一个代理connection对象，  
   并将其放入 DataSource 实例的某个与 DataSourceTransactionManager 相关的某处容器中。  
   在接下来的整个事务中，客户代码都应该使用该 connection 连接数据库，  
   执行所有数据库命令。  
   **不使用该 connection 连接数据库执行的数据库命令，在本事务回滚的时候得不到回滚**
    （物理连接 connection 逻辑上新建一个会话session；
   DataSource 与 TransactionManager 配置相同的数据源）

2. 事务结束时，回滚在第1步骤中得到的代理 connection 对象上执行的数据库命令，
   然后关闭该代理 connection 对象。
    （事务结束后，回滚操作不会对已执行完毕的SQL操作命令起作用）


##  @Transactional属性

属性 | 类型 | 描述
:-: | :-: | :-:
value | String | 可选的限定描述符，指定使用的事务管理器
propagation | enum: Propagation | 可选的事务传播行为设置
isolation | enum: Isolation | 可选的事务隔离级别设置
readOnly | boolean | 读写或只读事务，默认读写
timeout | int (in seconds granularity) | 事务超时时间设置
rollbackFor | Class对象数组，必须继承自Throwable | 导致事务回滚的异常类数组
rollbackForClassName | 类名数组，必须继承自Throwable | 导致事务回滚的异常类名字数组
noRollbackFor | Class对象数组，必须继承自Throwable | 不会导致事务回滚的异常类数组
noRollbackForClassName | 类名数组，必须继承自Throwable | 不会导致事务回滚的异常类名字数组


1. value ：主要用来指定不同的事务管理器；
   主要用来满足在同一个系统中，存在不同的事务管理器。
   比如在Spring中，声明了两种事务管理器txManager1, txManager2.然后，
   用户可以根据这个参数来根据需要指定特定的txManager.

2. value 适用场景：在一个系统中，需要访问多个数据源或者多个数据库，
   则必然会配置多个事务管理器的

3. REQUIRED_NEW：内部的事务独立运行，在各自的作用域中，可以独立的回滚或者提交；
   而外部的事务将不受内部事务的回滚状态影响。

4. NESTED 的事务，基于单一的事务来管理，提供了多个保存点。
   这种多个保存点的机制允许内部事务的变更触发外部事务的回滚。
   而外部事务在混滚之后，仍能继续进行事务处理，即使部分操作已经被混滚。
   由于这个设置基于 JDBC 的保存点，所以只能工作在 JDB C的机制。

5. rollbackFor：让受检查异常回滚；即让本来不应该回滚的进行回滚操作。

6. noRollbackFor：忽略非检查异常；即让本来应该回滚的不进行回滚操作。


## spring 事务特性
spring 所有的事务管理策略类都继承自 org.springframework.transaction.PlatformTransactionManager 接口
```java
    public interface PlatformTransactionManager {
      TransactionStatus getTransaction(TransactionDefinition definition)
      throws TransactionException;
      void commit(TransactionStatus status) throws TransactionException;
      void rollback(TransactionStatus status) throws TransactionException;
    }
```
### 事务的隔离级别(isolation)
指若干个并发的事务之间的隔离程度
1. `@Transactional(isolation = Isolation.READ_UNCOMMITTED)`：读取未提交数据(会出现脏读,
 不可重复读) 基本不使用
2. `@Transactional(isolation = Isolation.READ_COMMITTED)`：读取已提交数据(会出现不可重复读和幻读)
3. `@Transactional(isolation = Isolation.REPEATABLE_READ)`：可重复读(会出现幻读)
4. `@Transactional(isolation = Isolation.SERIALIZABLE)`：串行化


### 事务传播行为(propagation)
指若干个并发的事务之间的隔离程度  
如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为

1. `TransactionDefinition.PROPAGATION_REQUIRED`：
   如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
2. `TransactionDefinition.PROPAGATION_REQUIRES_NEW`：
   创建一个新的事务，如果当前存在事务，则把当前事务挂起。
3. `TransactionDefinition.PROPAGATION_SUPPORTS`：
   如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
4. `TransactionDefinition.PROPAGATION_NOT_SUPPORTED`：
   以非事务方式运行，如果当前存在事务，则把当前事务挂起。
5. `TransactionDefinition.PROPAGATION_NEVER`：
   以非事务方式运行，如果当前存在事务，则抛出异常。
6. `TransactionDefinition.PROPAGATION_MANDATORY`：
   如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
7. `TransactionDefinition.PROPAGATION_NESTED`：
   如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；
   如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。

## @Transactional失效场景

### 1、@Transactional 应用在非 public 修饰的方法上
如果`Transactional`注解应用在非public 修饰的方法上，Transactional将会失效。

![undefined](http://ww1.sinaimg.cn/large/005CzYvJgy1gdufhcczcqj30th0j2jro.jpg)

之所以会失效是因为在Spring AOP 代理时，如上图所示 TransactionInterceptor （事务拦截器）在目标方法执行前后进行拦截，DynamicAdvisedInterceptor（CglibAopProxy 的内部类）的 intercept 方法或 JdkDynamicAopProxy 的 invoke 方法会间接调用 AbstractFallbackTransactionAttributeSource的 computeTransactionAttribute 方法，获取Transactional 注解的事务配置信息。

```java
protected TransactionAttribute computeTransactionAttribute(Method method,
    Class<?> targetClass) {
        // Don't allow no-public methods as required.
        if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
        return null;
}
```

此方法会检查目标方法的修饰符是否为 public，不是 public则不会获取@Transactional 的属性配置信息。



**注意：** **`protected`、`private` 修饰的方法上使用 `@Transactional` 注解，虽然事务无效，但不会有任何报错，这是我们很容犯错的一点。**

### 2、@Transactional 注解属性 propagation 设置错误

这种失效是由于配置错误，若是错误的配置以下三种 propagation，事务将不会发生回滚。

- `TransactionDefinition.PROPAGATION_SUPPORTS`：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
- `TransactionDefinition.PROPAGATION_NOT_SUPPORTED`：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
- `TransactionDefinition.PROPAGATION_NEVER`：以非事务方式运行，如果当前存在事务，则抛出异常。



### 3、@Transactional 注解属性 rollbackFor 设置错误

`rollbackFor` 可以指定能够触发事务回滚的异常类型。

**Spring默认抛出了未检查`unchecked`异常（继承自 `RuntimeException`的异常）或者 `Error`才回滚事务；**

其他异常不会触发回滚事务。如果在事务中抛出其他类型的异常，但却期望 Spring 能够回滚事务，就需要指定 **rollbackFor**属性。

![undefined](http://ww1.sinaimg.cn/large/005CzYvJgy1gdufp1fpqzj30jy0cqt8y.jpg)



```java
// 希望自定义的异常可以进行回滚
@Transactional(propagation= Propagation.REQUIRED,rollbackFor= MyException.class
```

若在目标方法中抛出的异常是 `rollbackFor` 指定的异常的子类，事务同样会回滚。Spring源码如下：

```java
 	private int getDepth(Class<?> exceptionClass, int depth) {
        if (exceptionClass.getName().contains(this.exceptionName)) {
            // Found it!
            return depth;
        }
        // If we've gone as far as we can go and haven't found it...
        if (exceptionClass == Throwable.class) {
            return -1;
        }
        return getDepth(exceptionClass.getSuperclass(), depth + 1);
    }
```

### 4、同一个类中方法调用，导致@Transactional失效

开发中避免不了会对同一个类里面的方法调用，比如有一个类Test，它的一个方法A，A再调用本类的方法B（不论方法B是用public还是private修饰），但方法A没有声明注解事务，而B方法有。则外部调用方法A之后，方法B的事务是不会起作用的。这也是经常犯错误的一个地方。

那为啥会出现这种情况？其实这还是由于使用`Spring AOP`代理造成的，因为只有当事务方法被当前类以外的代码调用时，才会由`Spring`生成的代理对象来管理。

```java
    //@Transactional
    @GetMapping("/test")
    private Integer A() throws Exception {
        CityInfoDict cityInfoDict = new CityInfoDict();
        cityInfoDict.setCityName("2");
        /**
         * B 插入字段为 3的数据
         */this.insertB();/**
         * A 插入字段为 2的数据
         */int insert = cityInfoDictMapper.insert(cityInfoDict);

        return insert;
    }

    @Transactional()
    public Integer insertB() throws Exception {
        CityInfoDict cityInfoDict = new CityInfoDict();
        cityInfoDict.setCityName("3");
        cityInfoDict.setParentCityId(3);
        
        return cityInfoDictMapper.insert(cityInfoDict);
    }
```

### 5、异常被你的 catch“吃了”导致@Transactional失效

这种情况是最常见的一种@Transactional注解失效场景

```java
   @Transactional
   private Integer A() throws Exception {
       int insert = 0;
       try {
           CityInfoDict cityInfoDict = new CityInfoDict();
           cityInfoDict.setCityName("2");
           cityInfoDict.setParentCityId(2);
           /**
            * A 插入字段为 2的数据
             */
            insert = cityInfoDictMapper.insert(cityInfoDict);
            /**
             * B 插入字段为 3的数据
             */
            b.insertB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```



如果B方法内部抛了异常，而A方法此时try catch了B方法的异常，那这个事务还能正常回滚吗？

答案：不能！

会抛出异常：

```bash
org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
```

因为当`ServiceB`中抛出了一个异常以后，`ServiceB`标识当前事务需要`rollback`。但是`ServiceA`中由于你手动的捕获这个异常并进行处理，`ServiceA`认为当前事务应该正常`commit`。此时就出现了前后不一致，也就是因为这样，抛出了前面的`UnexpectedRollbackException`异常。

`spring`的事务是在调用业务方法之前开始的，业务方法执行完毕之后才执行`commit` or `rollback`，事务是否执行取决于是否抛出`runtime异常`。如果抛出`runtime exception` 并在你的业务方法中没有catch到的话，事务会回滚。

在业务方法中一般不需要catch异常，如果非要catch一定要抛出`throw new RuntimeException()`，或者注解中指定抛异常类型`@Transactional(rollbackFor=Exception.class)`，否则会导致事务失效，数据commit造成数据不一致，所以有些时候try catch反倒会画蛇添足。



### 6、数据库引擎不支持事务

这种情况出现的概率并不高，事务能否生效数据库引擎是否支持事务是关键。常用的MySQL数据库默认使用支持事务的 `innodb` 引擎。一旦数据库引擎切换成不支持事务的 `myisam`，那事务就从根本上失效了。


##  ... more




参考：  
- [@Transactional](https://blog.csdn.net/mingyundezuoan/article/details/79017659)
- [一口气说出 6种 @Transactional 注解失效场景](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650127149&idx=3&sn=1fb296df8328eb6fb694821d9f527cbf&chksm=f36ba60cc41c2f1aa1615b50f8cfe30da5656efea35628fcd6c85039fb4948d7086824208baf&scene=126&sessionid=1586923514&key=d85fbbed7c5c70d0e7f57c8e49b12367a76c76dfe00a442a1b1b862b85d21dbc5da9be134aee4a764fc766c5288517e20e9de5fa9c071e6040b70dabd562bf4043a8969a6bc2255ad551117d071158af&ascene=1&uin=MjQ1MzY1NzgxNw%3D%3D&devicetype=Windows+10&version=62080079&lang=zh_CN&exportkey=Az7foRVQJdDOtevqpIhnj%2FQ%3D&pass_ticket=77pGttf61abdl0lN%2FND%2B4916CzsWjv%2BORTQtCXQf%2FY0kPPzXN2V25G6hr03RxWa2)
