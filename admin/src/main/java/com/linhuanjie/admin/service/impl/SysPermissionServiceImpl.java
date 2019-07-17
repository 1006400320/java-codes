package com.linhuanjie.admin.service.impl;

import com.linhuanjie.admin.dao.MiaoUserMapper;
import com.linhuanjie.admin.dao.SysPermissionMapper;
import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.model.MiaoUserKey;
import com.linhuanjie.admin.model.SysPermissionKey;
import com.linhuanjie.admin.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private MiaoUserMapper miaoUserMapper;

    @Override
    public List<String> selectPermissionByUserId(Integer userId) {
        miaoUserMapper.selectByPrimaryKey(MiaoUserKey.builder().userId(userId).build());


        return null;
    }
}
