
# Java是值传递


#  科普下概念
## 实参与形参
> 我们都知道，在Java中定义方法的时候是可以定义参数的。  
> 比如Java中的main方法，public static void main(String[] args)，这里面的args就是参数。  
> 参数在程序语言中分为形式参数和实际参数。


- 形式参数：是在**定义函数名和函数体的时候**使用的参数,目的是用来接收调用该函数时传入的参数。
- 实际参数：在**调用有参函数时**，主调函数和被调函数之间有数据传递关系。在主调函数中调用一个函数时，函数名后面括号中的参数称为“实际参数”。

## 求值策略
> 在计算机科学中，求值策略是确定编程语言中表达式的求值的一组（通常确定性的）规则。  
> 求值策略定义何时和以何种顺序求值给函数的实际参数、什么时候把它们代换入函数、和代换以何种形式发生。

###  严格求值
1. 传值调用（值传递）（Call by value）  
 在传值调用中，实际参数先被求值，然后其值通过复制，被传递给被调函数的形式参数。因为形式参数拿到的只是一个”局部拷贝”，所以如果在被调函数中改变了形式参数的值，并不会改变实际参数的值。
2. 传引用调用（引用传递）（Call by reference）  
在传引用调用中，传递给函数的是它的实际参数的隐式引用而不是实参的拷贝。因为传递的是引用，所以，如果在被调函数中改变了形式参数的值，改变对于调用者来说是可见的。
3. 传共享对象调用（共享对象传递）（Call by sharing）  
传共享对象调用中，先获取到实际参数的地址，然后将其复制，并把该地址的拷贝传递给被调函数的形式参数。因为参数的地址都指向同一个对象，所以我们称也之为”传共享对象”，所以，如果在被调函数中改变了形式参数的值，调用者是可以看到这种变化的。

####  举个栗子：

1. 值传递
> 你有一把钥匙，当你的朋友想要去你家的时候，你复刻了一把新钥匙给他，自己的还在自己手里。
>这种情况下，他对这把钥匙做什么都不会影响你手里的这把钥匙。
2. 引用传递
> 你有一把钥匙，当你的朋友想要去你家的时候，如果你直接把你的钥匙给他了。
> 这种情况下，如果他对这把钥匙做了什么事情，比如他在钥匙上刻下了自己名字，那么这把钥匙还给你的时候，你自己的钥匙上也会多出他刻的名字。
3. 共享对象传递
> 你复制了一把你家里的钥匙给到你的朋友，他拿到钥匙以后，并没有在这把钥匙上做任何改动，而是通过钥匙打开了你家里的房门，进到屋里，把你家的电视给砸了。
> 这个过程，对你手里的钥匙来说，是没有影响的，但是你的钥匙对应的房子里面的内容却是被人改动了。

###  非严格求值（不展开讨论）
###  非确定性策略（不展开讨论）

# Java中的对象传递

## 官方文档
其实在 《The Java™ Tutorials》中，是有关于这部分内容的说明的。

首先是关于基本类型描述如下：
> Primitive arguments, such as an int or a double, are passed into methods by value. This means that any changes to the values of the parameters exist only within the scope of the method. When the method returns, the parameters are gone and any changes to them are lost.

**即，原始参数通过值传递给方法。这意味着对参数值的任何更改都只存在于方法的范围内。当方法返回时，参数将消失，对它们的任何更改都将丢失。**

关于对象传递的描述如下：

> Reference data type parameters, such as objects, are also passed into methods by value. This means that when the method returns, the passed-in reference still references the same object as before. However, the values of the object’s fields can be changed in the method, if they have the proper access level.

**也就是说，引用数据类型参数(如对象)也按值传递给方法。这意味着，当方法返回时，传入的引用仍然引用与以前相同的对象。但是，如果对象字段具有适当的访问级别，则可以在方法中更改这些字段的值。**

这一点官方文档已经很明确的指出了，Java就是值传递，只不过是把对象的引用当做值传递给方法。你细品，这不就是共享对象传递么？

其实Java中使用的求值策略就是传共享对象调用，也就是说，Java会将对象的地址的拷贝传递给被调函数的形式参数。

只不过”传共享对象调用”这个词并不常用，所以Java社区的人通常说”Java是传值调用”，这么说也没错，因为传共享对象调用其实是传值调用的一个特例。

## 举个栗子
![举个栗子.jpg](http://ww1.sinaimg.cn/large/005CzYvJgy1gduays08phj30go0dqq30.jpg)
```java
public static void main(String[] args) {
  Test pt = new Test();

  User hollis = new User();
  hollis.setName("Hollis");
  hollis.setGender("Male");
  pt.pass(hollis);
  System.out.println("print in main , user is " + hollis);
}

public void pass(User user) {
  user.setName("hollischuang");
  System.out.println("print in pass , user is " + user);
}

// 输出结果
// print in pass , user is User{name='hollischuang', gender='Male'}
// print in main , user is User{name='hollischuang', gender='Male'}
```
可以看到，对象类型在被传递到pass方法后，在方法内改变了其内容，最终调用方main方法中的对象也变了。

所以，很多人说，这和引用传递的现象是一样的，就是在方法内改变参数的值，会影响到调用方。

但是，其实这是走进了一个误区。

调用过程中实际上发生了什么？
![undefined](http://ww1.sinaimg.cn/large/005CzYvJgy1gdub12kr7mj30n40kcgnu.jpg)

在参数传递的过程中，实际参数的地址`0X1213456`被拷贝给了形参。这个过程其实就是值传递，只不过传递的值得内容是**对象的引用**。

[重温一下对象引用的例子](#L35)  
也就是说，Java对象的传递，是通过复制的方式把引用关系传递了，如果我们没有改引用关系，而是找到引用的地址，把里面的内容改了，是会对调用方有影响的，因为大家指向的是同一个共享对象。

那么，如果我们改动一下pass方法的内容：
```java
public void pass(User user) {
  user = new User();
  user.setName("hollischuang");
  System.out.println("print in pass , user is " + user);
}

//  在pass方法中，重新new了一个user对象，并改变了他的值，输出结果如下：
// print in pass , user is User{name='hollischuang', gender='Male'}
// print in main , user is User{name='Hollis', gender='Male'}
```
再看一下整个过程中发生了什么：
![undefined](http://ww1.sinaimg.cn/large/005CzYvJgy1gdub5l42t6j30nv0k1tba.jpg)

这个过程，就好像你复制了一把钥匙给到你的朋友，你的朋友拿到你给他的钥匙之后，找个锁匠把他修改了一下，他手里的那把钥匙变成了开他家锁的钥匙。这时候，他打开自己家，就算是把房子点了，对你手里的钥匙，和你家的房子来说都是没有任何影响的。

**所以，Java中的对象传递，如果是修改引用，是不会对原来的对象有任何影响的，但是如果直接修改共享对象的属性的值，是会对原来的对象有影响的。**

# 总结
- 求值策略：编程语言中进行方法间的参数传递的策略。
- 值传递和引用传递最大的区别：传递的过程中有没有复制出一个副本来，如果是传递副本，那就是值传递，否则就是引用传递。
- Java的参数传递是通过值传递实现的，只不过对于Java对象的传递，传递的内容是**对象的引用**。


> 参考 [我要彻底给你讲清楚，Java就是值传递，不接受争辩的那种！](http://www.hollischuang.com/archives/4640)



