package com.linhuanjie.designpattern.singleton;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020/4/16 16:21
 **/
public enum SingletonEnum {
    INSTANCE;

    public static void main(String[] args) {


        SingletonEnum INSTANCE1 = SingletonEnum.INSTANCE ;
        SingletonEnum INSTANCE2 = SingletonEnum.INSTANCE ;
        System.out.println(INSTANCE1 == INSTANCE2);

    }
}
