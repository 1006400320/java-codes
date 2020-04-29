package com.linhuanjie.javase.jvm;

import java.io.FileNotFoundException;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 自定义 ClassLoader
 * @CreateTime : 2020.04.29 20:59
 **/
public class CustomClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类：略
        return null;
    }

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();

        try {
            Class<?> clazz = Class.forName("One", true, customClassLoader);
            Object o = clazz.newInstance();
            System.out.println(o.getClass().getClassLoader());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
