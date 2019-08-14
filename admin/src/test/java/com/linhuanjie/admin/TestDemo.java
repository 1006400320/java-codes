package com.linhuanjie.admin;

import cn.hutool.core.util.RandomUtil;
import com.linhuanjie.common.utils.MD5Utils;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019.07.30 21:20
 * @email: lhuanjie@qq.com
 */
public class TestDemo {

    public static void main(String[] args) {
//        for (int i = 0; i < 20; i++) {
//            System.out.println(RandomUtil.randomInt(6));
//        }
//        System.out.println("====================");
//        for (int i = 0; i < 50; i++) {
//            System.out.print(RandomUtil.randomInt(1,6)+"||");
//        }
//        
        String test = MD5Utils.encrypt("test", "123");
        System.out.println("test = " + test);
        // 92fde850d824c2ba9b563cb6fa4078c3

    }
}
