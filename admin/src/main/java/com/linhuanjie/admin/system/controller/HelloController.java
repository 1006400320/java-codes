package com.linhuanjie.admin.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-03 21:27
 * @email: lhuanjie@qq.com
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping(value = "")
    public String hello(){
        return "hello springboot啊啊啊啊啊~";
    }

}
