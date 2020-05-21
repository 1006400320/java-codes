# fail-fast ( 快速失败 )
在使用迭代器遍历一个集合对象时,比如增强for,如果遍历过程中对集合对象的内容进行了修改(增删改),会抛出 `ConcurrentModificationException` 异常.

查看`ArrayList`源代码，在next方法执行的时候，会执行`checkForComodification()`方法

```java
@SuppressWarnings("unchecked")  
public E next() {  
    checkForComodification();  
    int i = cursor;  
    if (i >= size)  
        throw new NoSuchElementException();  
    Object[] elementData = ArrayList.this.elementData;  
    if (i >= elementData.length)  
        throw new ConcurrentModificationException();  
    cursor = i + 1;  
    return (E) elementData[lastRet = i];  
}  

//...............省略.............

final void checkForComodification() {  
    if (modCount != expectedModCount)  
        throw new ConcurrentModificationException();  
}  
```


原理:

1. 迭代器在遍历时直接访问集合中的内容,并且在遍历过程中使用一个modCount变量,
2. 集合中在被遍历期间如果内容发生变化,就会改变modCount的值,
3. 每当迭代器使用 hashNext()/next()遍历下一个元素之前,都会检测modCount变量和expectedmodCount值是否相等,如果相等就返回遍历,否则抛出异常,终止遍历.
4. 如果相等就返回遍历,否则抛出异常,终止遍历.




举例

```java
//会抛·出ConcurrentModificationException异常
for(Person person : Persons){
    if(person.getId()==2)
        student.remove(person);
}
```



注意
这里异常的抛出条件时检测到modCount = expectedmodCount 这个条件.

如果集合发生变化时修改modCount值, 刚好有设置为了expectedmodCount值, 则异常不会抛出.(比如删除了数据,再添加一条数据)

```java
//不会抛出ConcurrentModificationException异常
for(Person person : Persons){
    if(person.getId()==2){
        Persons.remove(person);
        Persons.add(new Person());
    }
}
```


所以不能依赖于这个异常是否抛出而进行并发操作的编程, 这个异常只建议检测并发修改的bug.

使用场景 :
java.util包下的集合类都是快速失败机制的, 不能在多线程下发生并发修改(迭代过程中被修改).

fail-safe ( 安全失败 )
采用安全失败机制的集合容器,在遍历时不是直接在集合内容上访问的,而是先copy原有集合内容,在拷贝的集合上进行遍历.

原理:

由于迭代时是对原集合的拷贝的值进行遍历,所以在遍历过程中对原集合所作的修改并不能被迭代器检测到,所以不会出发ConcurrentModificationException
缺点:

基于拷贝内容的优点是避免了ConcurrentModificationException,但同样地, 迭代器并不能访问到修改后的内容 (简单来说就是, 迭代器遍历的是开始遍历那一刻拿到的集合拷贝,在遍历期间原集合发生的修改迭代器是不知道的)
使用场景:
java.util.concurrent包下的容器都是安全失败的,可以在多线程下并发使用,并发修改.



参考： [fail-fast和fail-safe的介绍和区别](https://blog.csdn.net/Kato_op/article/details/80356618)