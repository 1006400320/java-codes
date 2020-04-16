package com.linhuanjie.designpattern.singleton;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 双重检查
 * @CreateTime : 2020/4/16 14:20
 **/
public class SingletonDemo {
    private static SingletonDemo singleton = null;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (singleton == null){
            System.out.println("啊，我被实例化了~");
            return new SingletonDemo();
        }

        return singleton;
    }


    public static void main(String[] args) {
        SingletonDemo instance1 = SingletonDemo.getInstance();
        SingletonDemo instance2 = SingletonDemo.getInstance();
        SingletonDemo instance3 = SingletonDemo.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
        System.out.println("instance3 = " + instance3);
    }
}
