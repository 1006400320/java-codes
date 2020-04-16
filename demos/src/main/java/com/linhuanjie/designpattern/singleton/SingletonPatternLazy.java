package com.linhuanjie.designpattern.singleton;

/**
 * 单例模式(懒汉式)
 */
class SingletonLazy {
    private static SingletonLazy singletonLazy;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        if (null == singletonLazy) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }
}


public class SingletonPatternLazy {
    public static void main(String[] args) {
        SingletonLazy s = SingletonLazy.getInstance();
        SingletonLazy s1 = SingletonLazy.getInstance();
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s == s1);
    }

}
