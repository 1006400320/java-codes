package com.linhuanjie.admin.system.service;

import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.common.result.Result;
import com.linhuanjie.common.service.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-07 10:29
 * @email: lhuanjie@qq.com
 */
public interface UserService extends Service<MiaoUser> {
    Result register(MiaoUser user, HttpServletRequest request);

    Result login(String keyword, String password, HttpServletRequest request);

    MiaoUser findByUserName(String userName);
}
