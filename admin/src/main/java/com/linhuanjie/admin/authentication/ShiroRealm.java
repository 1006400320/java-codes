package com.linhuanjie.admin.authentication;


import com.linhuanjie.admin.constant.AdminConstant;
import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.system.service.SysPermissionService;
import com.linhuanjie.admin.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

public class ShiroRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SysPermissionService sysPermissionService;


    /**
     * 授权模块，获取用户角色和权限
     *
     * @param principals principals
     * @return AuthorizationInfo 权限信息
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
     * 用户认证
     *
     * @param token AuthenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String userName = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String userName = upToken.getUsername();
        MiaoUser user = userService.findByUserName(userName);
        AuthenticationInfo info;

        if(user != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object principal =  user.getUserName();
            Object credentials = user.getPassword();

            //  获取盐值
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            info = new SimpleAuthenticationInfo(principal, credentials, salt,realmName);
        }else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }
        LOGGER.info("doGetAuthenticationInfo");
        return info;

//        if (user == null) {
//            throw new UnknownAccountException("账号或密码不正确");
//        }
//        if (!StringUtils.equals(password, user.getPassword())) {
//            throw new IncorrectCredentialsException("用户名或密码错误！");
//        }
//        if (AdminConstant.USER_STATUS_LOCK.equals(user.getUserStatus())) {
//            throw new LockedAccountException("账号已被锁定,请联系管理员！");
//        }
//
//        return new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
