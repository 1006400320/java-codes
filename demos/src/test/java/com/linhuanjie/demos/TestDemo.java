package com.linhuanjie.demos;

import org.junit.Test;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-01 23:07
 * @email: lhuanjie@qq.com
 */
public class TestDemo {
    public final String finalStr = null;
    {
        System.out.println("代码块");
    }
    static {
        System.out.println("静态代码块");
    }

    public TestDemo() {
        System.out.println("构造方法");
    }

    @Test
    public void test(){
        System.out.println("\"QAQ\" = " + "QAQ");
        
    }

    public static void main(String[] args) {
        TestDemo demo = new TestDemo();
        demo.finalStr = "QAQ";
        System.out.println();
    }
    
}
