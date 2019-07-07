package com.linhuanjie.admin.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.service.UserService;
import com.linhuanjie.common.result.Result;
import com.linhuanjie.common.result.ResultGenerator;
import io.micrometer.core.instrument.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-06 19:41
 * @email: lhuanjie@qq.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseBody
    public Result register(MiaoUser user) {
        if (user == null) {
            return ResultGenerator.genFailResult("请输入用户信息");
        }
        return userService.register(user);
    }


    @PostMapping("/login")
    public String login() {

        return "";
    }


}
