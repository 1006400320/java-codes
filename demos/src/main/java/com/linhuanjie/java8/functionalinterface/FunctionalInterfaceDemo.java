package com.linhuanjie.java8.functionalinterface;

import org.junit.Test;

/**
 * Java8  Lambda表达式 函数式接口
 *
 * @FunctionalInterface 修饰函数式接口的，要求接口中的抽象方法只有一个。 这个注解往往会和 lambda 表达式一起出现。
 * 原文： https://www.cnblogs.com/haixiang/p/11029639.html
 * @email : lhuanjie@qq.com
 * @create : 2020.05.23 11:55
 **/
public class FunctionalInterfaceDemo {

    @Test
    public void test1() {
        //无参无返回
        NoReturnNoParam noReturnNoParam = () -> {
            System.out.println("NoReturnNoParam");
        };
        noReturnNoParam.method();

        //一个参数无返回
        NoReturnOneParam noReturnOneParam = (int a) -> {
            System.out.println("NoReturnOneParam param:" + a);
        };
        noReturnOneParam.method(6);

        //多个参数无返回
        NoReturnMultiParam noReturnMultiParam = (int a, int b) -> {
            System.out.println("NoReturnMultiParam param:" + "{" + a + "," + +b + "}");
        };
        noReturnMultiParam.method(6, 8);

        //无参有返回值
        ReturnNoParam returnNoParam = () -> {
            System.out.print("ReturnNoParam");
            return 1;
        };
        int res = returnNoParam.method();
        System.out.println("return:" + res);

        //一个参数有返回值
        ReturnOneParam returnOneParam = (int a) -> {
            System.out.println("ReturnOneParam param:" + a);
            return 1;
        };
        int res2 = returnOneParam.method(6);
        System.out.println("return:" + res2);

        //多个参数有返回值
        ReturnMultiParam returnMultiParam = (int a, int b) -> {
            System.out.println("ReturnMultiParam param:" + "{" + a + "," + b + "}");
            return 1;
        };
        int res3 = returnMultiParam.method(6, 8);
        System.out.println("return:" + res3);
    }

    @Test
    public void test2() {
        // 1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
        NoReturnMultiParam lamdba1 = (a, b) -> {
            System.out.println("简化参数类型");
        };
        lamdba1.method(1, 2);

        // 2.简化参数小括号，如果只有一个参数则可以省略参数小括号
        NoReturnOneParam lambda2 = a -> {
            System.out.println("简化参数小括号");
        };
        lambda2.method(1);

        // 3.简化方法体大括号，如果方法条只有一条语句，则可以省略方法体大括号
        NoReturnNoParam lambda3 = () -> System.out.println("简化方法体大括号");
        lambda3.method();

        // 4.如果方法体只有一条语句，并且是 return 语句，则可以省略方法体大括号和 return
        ReturnOneParam lambda4 = a -> a + 3;
        System.out.println(lambda4.method(5));

        ReturnMultiParam lambda5 = (a, b) -> a + b;
        System.out.println(lambda5.method(1, 1));
    }
}

/**
 * 无参无返回值
 */
@FunctionalInterface
interface NoReturnNoParam {
    void method();
}

/**
 * 一个参数无返回
 */
@FunctionalInterface
interface NoReturnOneParam {
    void method(int a);
}

/**
 * 多参数无返回
 */
@FunctionalInterface
interface NoReturnMultiParam {
    void method(int a, int b);
}

/*** 无参有返回*/
@FunctionalInterface
interface ReturnNoParam {
    int method();
}

/**
 * 一个参数有返回值
 */
@FunctionalInterface
interface ReturnOneParam {
    int method(int a);
}

/**
 * 多个参数有返回值
 */
@FunctionalInterface
interface ReturnMultiParam {
    int method(int a, int b);
}
