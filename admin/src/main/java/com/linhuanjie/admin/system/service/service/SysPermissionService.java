package com.linhuanjie.admin.system.service.service;

import java.util.List;

public interface SysPermissionService {

    /**
     * 根据userId 获取权限
     * @param userId
     * @return
     */
    List<String> selectPermissionByUserId(Integer userId);
}
