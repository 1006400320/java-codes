
- @Data：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
- @Setter：注解在属性上；为属性提供 setting 方法
- @Setter：注解在属性上；为属性提供 getting 方法
- @Log4j：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
- @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
- @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
- @Cleanup： 可以关闭流
- @Builder： 被注解的类加个构造者模式
- @Synchronized： 加个同步锁
- @SneakyThrows： 等同于try/catch 捕获异常
- @NonNull： 如果给参数加个这个注解 参数为null会抛出空指针异常
- @Value： 注解和@Data类似，区别在于它会把所有成员变量默认定义为private final修饰，并且不会生成set方法。
- @ToString(exclude = {"id","name"})： 自定义toString方法，排除某些字段

idea使用lombok需如下配置：  
检查Annotation Processors -> Enable annotation processing 是否勾选
![idea](http://ww1.sinaimg.cn/large/005CzYvJgy1gdq7u1zsphj30u70ju75x.jpg)

[lombok 原理&简单实现](https://www.tuicool.com/articles/y6rUz2V)


> [原文链接](https://blog.csdn.net/u011308294/java/article/details/78627560)  
> [官方文档](https://projectlombok.org/features/all)  
> [lombok注解介绍](http://blog.csdn.net/sunsfan/article/details/53542374)  
> [Lombok介绍及使用方法](http://www.cnblogs.com/holten/p/5729226.html)