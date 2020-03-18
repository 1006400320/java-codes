package com.linhuanjie.demo;

import com.google.common.hash.Funnels;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-11-29 10:07
 * @email: lhuanjie@qq.com
 */
public class TestDemo {
    public static void main(String[] args) {
            // create
//            CuckooFilter<Integer> filter = new CuckooFilter.Builder<>(Funnels.integerFunnel(), 2000000).build();
//            // insert
//            if (filter.put(42)) {
//                System.out.println("Insert Success!");
//            }
//            // contains
//            if (filter.mightContain(42)) {
//                System.out.println("Found 42!");
//            }
//            // count
//            System.out.println("Filter has " + filter.getCount() + " items");
//
//            // count
//            System.out.println("42 has been inserted approximately " + filter.approximateCount(42) + " times");
//
//            // % loaded
//            System.out.println("Filter is " + String.format("%.0f%%", filter.getLoadFactor() * 100) + " loaded");
//
//            // delete
//            if (filter.delete(42)) {
//                System.out.println("Delete Success!");
//            }


            Integer a = 1;
            Integer b = 2;
            System.out.println(a.compareTo(b));



    }

}