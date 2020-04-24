
# 导语
开发中经常见到entity 对象中会实现 `Serializable`接口，定义 `serialVersionUID`属性。如下
```java
public class User implements Serializable {
    private static final long serialVersionUID = -4171074367034581463L;
    // 省略...
}
```
那么这个 serialVersionUID 有什么用呢？

# serialVersionUID 是什么

`serialVersionUID`适用于Java的序列化机制。  
**简单来说，Java的序列化机制是通过判断类的`serialVersionUID`来验证版本一致性的。**

在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体类的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常，即是`InvalidCastException`。

具体的序列化过程是这样的：

序列化操作时，系统会把当前类的serialVersionUID写入到序列化文件中，

当反序列化时系统会去检测文件中的serialVersionUID，判断它是否与当前类的serialVersionUID一致，如果一致就说明序列化类的版本与当前类版本是一样的，可以反序列化成功，否则失败。



# serialVersionUID的两种生成方式   

## 一、是默认的1L



private static final long serialVersionUID = 1L;     

## 二、是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段     

private static final long  serialVersionUID = xxxxL;

1. 类实现Serializable接口
2. IDEA 安装插件 `GenerateSerialVersionUID`, 按 alt + ins 生成

当实现java.io.Serializable接口的类**没有显式地定义一个serialVersionUID变量时候，Java序列化机制会根据编译的Class自动生成一个serialVersionUID作序列化版本比较用**，这种情况下，如果Class文件(类名，方法明等)没有发生变化(增加空格，换行，增加注释等等)，就算再编译多次，serialVersionUID也不会变化的。

如果我们不希望通过编译来强制划分软件版本，即实现序列化接口的实体能够兼容先前版本，就需要显式地定义一个名为serialVersionUID，类型为long的变量，不修改这个变量值的序列化实体都可以相互进行串行化和反串行化。



























