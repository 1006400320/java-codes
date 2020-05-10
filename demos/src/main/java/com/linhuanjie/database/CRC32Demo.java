package com.linhuanjie.database;

import java.util.zip.CRC32;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 *              如果有个需要用身份证号来查询的业务，怎么给字段加索引
 *              可以加个整型字段来给身份证号做crc32() 函数存储，并且加索引，
 *              查询的时候除了查这个字段还要再跟身份证号匹配防止存储的字段重复
 *
 * @CreateTime : 2019.11.20 22:01
 **/
public class CRC32Demo {
    public static void main(String[] args) {
        CRC32 a = new CRC32();
        // 126491095
        a.update("a".getBytes());
        System.out.println("a.getValue() = " + a.getValue());
    }
}


