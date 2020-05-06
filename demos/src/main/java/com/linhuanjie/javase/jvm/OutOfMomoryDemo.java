package com.linhuanjie.javase.jvm;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 1、获取内存详情：jmap -dump:format=b,file=e.bin pid
 * 这种方式可以用 jvisualvm.exe 进行内存分析，或者采用 Eclipse Memory Analysis Tools (MAT)这个工具
 * <p>
 * 2. 获取内存dump：  jmap -histo:live pid
 * 这种方式会先触发fullgc，所有如果不希望触发fullgc 可以使用jmap -histo pid
 * <p>
 * 3.第三种方式：jdk启动加参数：
 * -XX:+HeapDumpBeforeFullGC 
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=F:\dump
 * 这种方式会产生dump日志，再通过jvisualvm.exe 或者Eclipse Memory Analysis Tools 工具进行分析
 * <p>
 * 设置堆大小  -Xms20m -Xmx20m
 *
 * @author : linhuanjie
 * @createtime : 2020/5/6 14:39
 **/
public class OutOfMomoryDemo {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        for (int i = 0; i < 1000000000; i++) {
            OOMObject o = new OOMObject();
            o.setId(i);
            o.setName("Name" + i);
            map.put(i, o);
        }
    }
}

class OOMObject {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
