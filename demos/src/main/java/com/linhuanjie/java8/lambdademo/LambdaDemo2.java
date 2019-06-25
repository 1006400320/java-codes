package com.linhuanjie.java8.lambdademo;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-10-13 19:50
 * @email: lhuanjie@qq.com
 */
public class LambdaDemo2 {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());


        System.out.println("========================");
//        List<Integer> ints = Arrays.asList(1,2,3,4,null);  // 若arrays 里有null,则会报NullPointException
        List<Integer> ints = Arrays.asList(1,2,3,4);
        Optional.ofNullable(ints);
        Stream<Integer> integerStream = ints.stream().filter(i -> i > 2);
        integerStream.forEach(System.out::println);

        System.out.println("========================");
        String[] strs = new String[]{"11","222","333"};
        Arrays.stream(strs).forEach(System.out::println);

        System.out.println("========================");
        Random r = new Random();
        r.ints(2).limit(10).forEach(System.out::println);

        System.out.println("========================");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().sorted().collect(Collectors.toList());
        for (Integer integer : squaresList) {
            System.out.println(integer);
        }

        System.out.println("==============一个并行执行的流【parallelStream】==========");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums.parallelStream().forEach(System.out::println);
        System.out.println("========================");
        nums.parallelStream().forEachOrdered(System.out::println);

        System.out.println("========================");
        System.out.println("【Collectors】");
        List<String> ss = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered2 = ss.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered2);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);


        System.out.println("【找出标题最长的字符串】========================");
        List<String> tasks = Arrays.asList("2","s","sss");
        String collect = tasks.stream().collect(Collectors.collectingAndThen(Collectors.maxBy((String t1, String t2) -> t1.length() - t2.length()), Optional::get));
        System.out.println(collect);
        String collect1 = tasks.stream().collect(Collectors.joining(";"));
        System.out.println(collect1);

        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2=" + value2);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println(identity);

    }



}
