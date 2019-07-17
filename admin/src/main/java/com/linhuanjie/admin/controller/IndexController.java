package com.linhuanjie.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-05 21:50
 * @email: lhuanjie@qq.com
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/login")
    public String toLogin(HttpServletRequest request){
        Object miaoUser = request.getSession().getAttribute("miao_user");

        if (miaoUser == null) {
            return "login";
        }

        return "home";
    }

    @GetMapping(value = "/register")
    public String toRegister(){
        LOGGER.info("toRegister");
        return "register";
    }

    @GetMapping(value = "/home")
    public String toHome(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("toHome");
        Object userSession = request.getSession().getAttribute("miao_user");
        if(userSession == null){
            return "login";
        }
        request.setAttribute("userSession",userSession);
        LOGGER.info("userSession:{}",userSession);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Object miaoUser = request.getSession().getAttribute("miao_user");

        if (miaoUser == null) {
            return "login";
        }
        request.getSession().removeAttribute("miao_user");
        return "login";
    }


    @GetMapping(value = "/goodsList")
    public String toGoodsList(){
        LOGGER.info("toGoodsList");
        return "goodsList";
    }
}
