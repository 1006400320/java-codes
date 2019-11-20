package com.linhuanjie.spring;


/**
 * @author: linhuanjie
 * @description:   用更优雅的方式发HTTP请求(RestTemplate详解)（https://www.cnblogs.com/javazhiyin/p/9851775.html）
 * @createTime : 2018-11-29 10:07
 * @email: lhuanjie@qq.com
 */

public class RestTemplateDemo {

//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject("ly.mama.cn/main/index", String.class);
//        System.out.println("forObject = " + forObject);
//
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("ly.mama.cn/main/index", String.class);
//        System.out.println("forEntity = " + forEntity);


//    }
    public static void main(String[] args)  {
        String a = "hello2";
        final String b = getHello();
        String c = b + 2;
        String d = "hello" + 2;
        System.out.println((a == c));
        System.out.println((a == d));
        System.out.println((a));
        System.out.println((b));
        System.out.println((c));

    }

    public static String getHello() {
        return "hello";
    }

}

class Test {
    public static void main(String[] args)  {
//        MyClass myClass = new MyClass();
//        StringBuffer buffer = new StringBuffer("hello");
//        myClass.changeValue(buffer);
//        System.out.println(buffer.toString());

        String a = "111";
        System.out.println("a = " + a);
        System.out.println("a = " + test(a));
        System.out.println("a = " + a);

    }

    public static String test(String a ){
        a = a + "QAQ";
        return a;
    }
}

class MyClass {

    void changeValue(final StringBuffer buffer) {
        buffer.append("world");
    }
}