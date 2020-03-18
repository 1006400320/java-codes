package com.linhuanjie.guava;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019.10.17 20:04
 * @email: lhuanjie@qq.com
 */
public class GuavaListsDemo {

    public static void main(String[] args) {

        Function<String, Integer> strlen = new Function<String, Integer>() {
            @Override
            public Integer apply(String from) {
                Preconditions.checkNotNull(from);
                return from.length();
            }
        };
        List<String> from = Lists.newArrayList("abc", "defg", "hijkl");
        List<Integer> to = Lists.transform(from, strlen);
        for (int i = 0; i < from.size(); i++) {
            System.out.printf("%s has length %d\n", from.get(i), to.get(i));
        }


        Function<String, Boolean> isPalindrome = new Function<String, Boolean>() {
            @Override
            public Boolean apply(String from) {
                Preconditions.checkNotNull(from);
                return new StringBuilder(from).reverse().toString().equals(from);
            }
        };
        from = Lists.newArrayList("rotor", "radar", "hannah", "level", "botox");
        List<Boolean> to1 = Lists.transform(from, isPalindrome);
        for (int i = 0; i < from.size(); i++) {
            System.out.printf("%s is%sa palindrome\n", from.get(i), to1.get(i) ? " " : " NOT ");
        }

        // changes in the "from" list are reflected in the "to" list
        System.out.printf("\nnow replace hannah with megan...\n\n");
        from.set(2, "megan");
        for (int i = 0; i < from.size(); i++) {
            System.out.printf("%s is%sa palindrome\n", from.get(i), to1.get(i) ? " " : " NOT ");
        }


    }



}
