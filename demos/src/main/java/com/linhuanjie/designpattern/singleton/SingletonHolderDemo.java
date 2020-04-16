package com.linhuanjie.designpattern.singleton;


import org.omg.SendingContext.RunTime;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :  延迟类初始化
 *                          要想很简单地实现线程安全，可以采用静态初始化器的方式，它可以由JVM来保证线程的安全性。
 *                          比如前面的饿汉式实现方式，在类装载的时候就初始化对象，不管是否需要，存在一定的空间浪费。
 *                          一种可行的方式就是采用类级内部类，在这个类级内部类里面去创建对象实例。这样一来，只要不使用到这个类级内部类，那就不会创建对象实例，从而同时实现延迟加载和线程安全。
 * @CreateTime : 2020/4/16 16:58
 **/
public class SingletonHolderDemo {

    /**
     * 类级内部类
     */
    private static class SingletonHolder {
        private static SingletonHolderDemo lazySingleton = new SingletonHolderDemo() ;
    }
    public static SingletonHolderDemo getInstance (){
        return SingletonHolder.lazySingleton ;
    }
    public static void main(String[] args) {
        SingletonHolderDemo lazySingleton1 = SingletonHolderDemo.getInstance() ;
        SingletonHolderDemo lazySingleton2 = SingletonHolderDemo.getInstance() ;
        System.out.println(lazySingleton1 == lazySingleton2);
        System.out.println(lazySingleton1+";;"+lazySingleton2);

    }


}
