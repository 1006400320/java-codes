

如果要进行`remove`操作，可以调用迭代器的 `remove`方法而不是集合类的 remove 方法。

因为如果列表在任何时间从结构上修改创建迭代器之后，以任何方式除非通过迭代器自身`remove/add`方法，迭代器都将抛出一个`ConcurrentModificationException`,这就是单线程状态下产生的 **fail-fast 机制**。

> **fail-fast 机制** ：多个线程对 fail-fast 集合进行修改的时，可能会抛出ConcurrentModificationException，单线程下也会出现这种情况，上面已经提到过。

`java.util`包下面的所有的集合类都是fail-fast的，而`java.util.concurrent`包下面的所有的类都是fail-safe的。

![不要在 foreach 循环里进行元素的 remove/add 操作](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019/7/foreach-remove:add.png)