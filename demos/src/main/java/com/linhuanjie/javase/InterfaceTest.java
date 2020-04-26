package com.linhuanjie.javase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

interface IAmInterface {
    int num = 1;

    void method();
}

public class InterfaceTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.linhuanjie.javase.IAmInterface");
        System.out.println(clazz.getField("num"));


        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println("field：" + field);
        }

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }


    }

}
