package com.linhuanjie.java8.lambdademo;

import org.junit.Test;


@FunctionalInterface
interface Interface1 {
    public void print();
}

public class LambdaTest {
    public static void main(String[] args) {
        Interface1 i = new Interface1() {
            @Override
            public void print() {
                System.out.println("QAQ");
            }
        };

        Interface1 interface1 = () -> System.out.println(" I am lambda Demo");
        interface1.print();

    }


    @Test
    public void demo1() {
//        DoubleSupplier doubleSupplier = () -> 5;
//        ResourceId resourceId = () -> "QAQ";
//        System.out.println(doubleSupplier.getAsDouble());
//        System.out.println(resourceId.getAccuracy());
//        System.out.println(resourceId.getName());
    }
}
