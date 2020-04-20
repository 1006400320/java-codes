# java中为final变量赋值的几种方式

## 前言

> 使用final修饰变量，很多人第一时间想到的就是不可变。然后以为变量必须得在声明的时候就为其赋初始值，其实不然，本文将详细讲解java中使用final修改的变量的赋值问题。

## 被final修饰的变量的几种赋值方式

1、被`final`修饰的变量有三种赋值方式。
2、被`final static`修饰的变量有两种赋值方式。

储备知识：在类的加载中，类的加载顺序我们应该都知道，静态代码块 -> 构造代码块 -> 构造方法

精华：

> 当类被加载进内存的时候，这个属性只是声明了一个变量，并没有给分配内存空间，只有当类在被实例化的时候才分配了内存空间，而这个时候又同时执行了构造方法， 所以属性被初始化了，也就符合了当它被分配内存空间的时候就需要实例化，以后不在改变的条件。

## 被final修饰的变量

### 1、在定义时直接赋值

```java
public class Test {
    public final String str = "mark";
}
```

### 2、声明时不赋值，在构造方法中进行赋值

```java
public class Test {
    public final String str;
    public Test() {
    	this.str = "mark";
    }
}
```
### 3、声明时不赋值，在代码块中进行赋值

```java
public class Test {
    public final String str;
	{
    	this.str = "mark";
	}
}
```

当你发现一个类的变量被final修饰但没被赋值，那么可以判断出它肯定会在构造函数里被赋值。（因为倘若是set方法赋值，线程是不安全的，因为set方法可以被调用多次，而final变量只能被赋值一次）

## 被final static修饰的变量

### 1、在定义时直接赋值

```java
public class Test {
    public static final String str = "mark";   
}
```

### 2、在静态代码块里面进行赋值

```java
public class Test {
    public static final String str;
    static {
        str = "mark";
    }
}
```
>  final变量会经过jvm进行优化，所以平时使用过程中建议使用final来修饰变量。

> 原文：[java中为final变量赋值的几种方式](https://blog.csdn.net/qq_34626859/java/article/details/89157740)