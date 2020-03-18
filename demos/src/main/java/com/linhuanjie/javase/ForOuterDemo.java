package com.linhuanjie.javase;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 原文：（break outer,continue outer的使用）https://www.cnblogs.com/HDK2016/p/6740541.html
 * @CreateTime : 2019-11-20 20:28
 **/
public class ForOuterDemo {

    public static void main(String[] args) {
        outer:
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                System.out.println("i:"+i+" j:"+j);
                if(j==1){
                    continue outer;
                }
                if(i==1){
                    break outer;
                }
            }
            System.out.println("i:"+i);
        }
    }

}
