package com.linhuanjie.random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :  RandomStringUtils随机生成工具
 * @CreateTime : 2019-10-29 10:25
 **/
public class RandomStringUtilsDemo {

    public static void main(String[] args) {
        //产生5位长度的随机字符串，中文环境下是乱码
        String random = RandomStringUtils.random(5);
        System.out.println(random);

        //使用指定的字符生成5位长度的随机字符串
        random = RandomStringUtils.random(5, new char[]{'a', 'b', 'c', 'd', 'e', 'f', '1', '2', '3'});
        System.out.println(random);

        //生成指定长度的字母和数字的随机组合字符串
        random = RandomStringUtils.randomAlphanumeric(5);
        System.out.println(random);

        //生成随机数字字符串
        random = RandomStringUtils.randomNumeric(5);
        System.out.println(random);

        //生成随机[a-z]字符串，包含大小写
        random = RandomStringUtils.randomAlphabetic(5);
        System.out.println(random);

        //生成从ASCII 32到126组成的随机字符串
        random = RandomStringUtils.randomAscii(4);
        System.out.println(random);
    }
}
