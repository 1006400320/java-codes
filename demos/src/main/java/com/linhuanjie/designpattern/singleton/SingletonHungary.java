package com.linhuanjie.designpattern.singleton;

/**
 * 单例模式(饿汉式)
 */
public class SingletonHungary {
    // 创建 static 类属性
    private final static SingletonHungary SINGLETON = new SingletonHungary();

    /**
     * 私有化构造方法
     */
    private SingletonHungary() {
    }

    public static SingletonHungary getInstance() {
        return SINGLETON;
    }


    public static void main(String[] args) {
        SingletonHungary s1 = SingletonHungary.getInstance();
        SingletonHungary s2 = SingletonHungary.getInstance();

        System.out.println(s1);
        System.out.println(s2);

    }
}
