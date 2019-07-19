package com.linhuanjie.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: linhuanjie
 * @description: todo 菜单权限管理
 * @createTime : 2019.07.09 21:59
 * @email: lhuanjie@qq.com
 */
@RestController
@RequestMapping(value = "permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);


    @GetMapping("getUserPerms")
    @ResponseBody
    public Map<String, Object> getUserPerms(){
        logger.info("获取登陆用户的权限");
        Map<String, Object> data = new HashMap<>();
//        MiaoUser user = (BaseAdminUser) SecurityUtils.getSubject().getPrincipal();
//        data = permissionService.getUserPerms(user);
        return data;
    }

}
