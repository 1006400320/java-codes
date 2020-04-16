package com.linhuanjie.designpattern.singleton;

/**
 * 单例模式(饿汉式)
 */
class Singleton{
    // 创建 static 类属性
    private final static Singleton SINGLETON = new Singleton();
    /**
     * 私有化构造方法
     */
    private Singleton(){
    }

    public static Singleton getInstance(){
        return SINGLETON;
    }
}

public class SingletonPatternHungary {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = Singleton.getInstance();
        Singleton s4 = Singleton.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);

        System.out.println(s1==s2);
        System.out.println(s1==s3);
        System.out.println(s1==s4);
        System.out.println(s2==s4);
    }
}
