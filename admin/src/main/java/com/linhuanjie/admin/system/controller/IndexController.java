package com.linhuanjie.admin.system.controller;

import com.linhuanjie.admin.model.MiaoUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    /**
     * 首页，并将登录用户的全名返回前台
     * @param model
     * @return
     */
    @GetMapping(value={"/","/common/index","/index"})
    public String index(Model model){

        MiaoUser user = (MiaoUser) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("userName", user.getUserName());
        return "index";
    }

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
