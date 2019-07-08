package com.linhuanjie.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-03 22:00
 * @email: lhuanjie@qq.com
 */
@Controller
public class PublicController {

    @GetMapping(value={"/","/common/index","/index"})
    public String index(){
        // todo 判断有没有登录
//        if( 登录 )
//        return "redirect:/home";

        return "redirect:/login";
    }


}
