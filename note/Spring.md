

# @Transactional注解
@Transactional 是声明式事务管理 编程中使用的注解
> 将标签放置在需要进行事务管理的方法上，而不是放在所有接口实现类上。  
只读的接口就不需要事务管理，由于配置了@Transactional就需要AOP拦截及事务的处理，**可能影响系统性能**。
##  1 .添加位置
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


## @Transactional 实现原理：
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
### 事务的隔离级别
指若干个并发的事务之间的隔离程度
1. `@Transactional(isolation = Isolation.READ_UNCOMMITTED)`：读取未提交数据(会出现脏读,
 不可重复读) 基本不使用
2. `@Transactional(isolation = Isolation.READ_COMMITTED)`：读取已提交数据(会出现不可重复读和幻读)
3. `@Transactional(isolation = Isolation.REPEATABLE_READ)`：可重复读(会出现幻读)
4. `@Transactional(isolation = Isolation.SERIALIZABLE)`：串行化


### 事务传播行为
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


指若干个并发的事务之间的隔离程度

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

##  ... more




参考： [@Transactional](https://blog.csdn.net/mingyundezuoan/article/details/79017659)

