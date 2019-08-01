package com.linhuanjie.admin.shiro;


import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.service.SysPermissionService;
import com.linhuanjie.admin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SysPermissionService sysPermissionService;


    /**
     * 授权验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        MiaoUser user = (MiaoUser) principals.getPrimaryPrincipal();
        List<String> sysPermissions = sysPermissionService.selectPermissionByUserId(user.getUserId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(sysPermissions);
        LOGGER.info("doGetAuthorizationInfo");
        return info;
    }

    /**
     * 登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        MiaoUser user = userService.findByUserName(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 验证密码
//        if (!user.getPassword().equals(new String(token.getPassword()))) {
//            throw new UnknownAccountException("账号或密码不正确");
//        }

        LOGGER.info("doGetAuthenticationInfo");
        return new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
