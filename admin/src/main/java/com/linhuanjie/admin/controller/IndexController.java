package com.linhuanjie.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-05 21:50
 * @email: lhuanjie@qq.com
 */
@RequestMapping
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/login")
    public String toLogin(){
        logger.info("toLogin");
        return "login";
    }


}
