package com.linhuanjie.java8;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 优雅的避免 NullPointerException
 * https://www.yuque.com/ey49ng/ebksdf/feb50u
 *
 * @author : linhuanjie
 * @email : lhuanjie@qq.com
 * @create : 2020.08.02 18:01
 **/
public class OptionalDemo {

    public static void main(String[] args) {
        // of
        // 调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("奥利给");
        // 传入参数为 null, 则会抛出 NullPointerException. NPE
        Optional.of(null);


        // ofNullable
        Optional empty = Optional.ofNullable(null);

        // get
        try {
            Optional.empty().get();
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage()); // No value present
        }

        // map
        String nameStr = "hello world";
        String upperName = Optional.ofNullable(nameStr)
                .map(n -> n.toUpperCase())
                .get();
        System.out.println(upperName);

        // flatMap
        upperName = Optional.ofNullable(nameStr)
                .flatMap(n -> Optional.ofNullable(n.toUpperCase()))
                .get();
        System.out.println(upperName);

        // isPresent
        Integer i = new Integer(1);
        boolean isPresent = Optional.ofNullable(i).isPresent();
        System.out.println(isPresent);

        // ifPresent
        Optional.of(nameStr).ifPresent(System.out::println);

        // orElse
        String nullStr = null;
        String result = Optional.ofNullable(nullStr).orElse("空空如也~");
        System.out.println(result);

        // orElseGet
        result = Optional.ofNullable(nullStr).orElseGet(String::new);
        System.out.println(result);

        // orElseThrow
        try {
            Optional.empty().orElseThrow(IllegalArgumentException::new);
        } catch (Throwable ex) {
            System.err.println("error:" + ex.getMessage());
        }

        // filter
        List<String> names = Arrays.asList("YanWei", "YanTian");
        for (String s : names) {
            Optional<String> nameLenLessThan7 = Optional.of(s).filter((value) -> value.length() < 7);
            System.out.println(nameLenLessThan7.orElse("All of names are more than 6 characters"));
        }

    }
}
