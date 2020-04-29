package com.linhuanjie.javase.jvm;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 获取类加载器
 * @CreateTime : 2020.04.29 20:25
 **/
public class ClassLoaderDemo {

    public static void main(String[] args) {
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);

        // sun.misc.Launcher$ExtClassLoader@5ce65a89
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("extClassLoader = " + extClassLoader);

        // null （bootstrapClassLoader是 c 或 c++ 写的，所以获取不到 ）
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("bootstrapClassLoader = " + bootstrapClassLoader);

        // sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader appClassLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("appClassLoader = " + appClassLoader);

        // null （bootstrapClassLoader是 c 或 c++ 写的，所以获取不到 ）
        ClassLoader bootstrapClassLoader2 = String.class.getClassLoader();
        System.out.println("bootstrapClassLoader2 = " + bootstrapClassLoader2);
    }

}
