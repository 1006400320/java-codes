package com.linhuanjie.admin.system.controller;

import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.system.service.service.UserService;
import com.linhuanjie.common.result.Result;
import com.linhuanjie.common.result.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-06 19:41
 * @email: lhuanjie@qq.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseBody
    public Result register(MiaoUser user, HttpServletRequest request) {
        if (user == null || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.genFailResult("请输入用户信息");
        }
        return userService.register(user,request);
    }


    /**
     * 登录
     * @param keyword 账号/邮箱
     * @param password 密码
     * @param request
     * @param rememberMe todo 记住我
     * @return
     */
    @PostMapping("/login")
    public Result login(String keyword,String password, HttpServletRequest request, boolean rememberMe) {
//        原始版
//        if (StringUtils.isEmpty(keyword) || StringUtils.isEmpty(password)) {
//            return ResultGenerator.genFailResult("请输入用户信息");
//        }
//        return userService.login(keyword,password,request);

        // 获取当前的Subject
        Subject subject = SecurityUtils.getSubject();

        // shiro 版
        UsernamePasswordToken token = new UsernamePasswordToken(keyword,password); // todo rememberMe

        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            subject.login(token);
            return ResultGenerator.genSuccessResult("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return ResultGenerator.genFailResult("登录失败");

    }


}
