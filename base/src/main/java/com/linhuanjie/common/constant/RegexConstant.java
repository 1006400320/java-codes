package com.linhuanjie.common.constant;

import cn.hutool.core.util.ReUtil;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 正则表达式常量
 * @CreateTime : 2020.03.18 23:38
 **/
public class RegexConstant {

    /** 图片 */
    public static String PIC = ".+(.JPEG|.jpeg|.JPG|.jpg)$";


    public static void main(String[] args) {
        boolean match = ReUtil.isMatch(RegexConstant.PIC, "aaa.jpg");
        System.out.println("match = " + match);
    }

}
