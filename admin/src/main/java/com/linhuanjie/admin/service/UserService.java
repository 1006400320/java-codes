package com.linhuanjie.admin.service;

import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.common.result.Result;
import com.linhuanjie.common.service.Service;

import java.util.List;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-07 10:29
 * @email: lhuanjie@qq.com
 */
public interface UserService extends Service<MiaoUser> {
    Result register(MiaoUser user);
}
