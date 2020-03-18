package com.linhuanjie.admin.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.linhuanjie.admin.constant.AdminConstant;
import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.system.dao.MiaoUserMapper;
import com.linhuanjie.admin.system.service.UserService;
import com.linhuanjie.common.result.Result;
import com.linhuanjie.common.result.ResultGenerator;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-07 10:30
 * @email: lhuanjie@qq.com
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private MiaoUserMapper miaoUserMapper;

    @Override
    public void save(MiaoUser model) {

    }

    @Override
    public void save(List<MiaoUser> models) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteByIds(String ids) {

    }

    @Override
    public void update(MiaoUser model) {

    }

    @Override
    public MiaoUser findById(Integer id) {
        return null;
    }

    @Override
    public MiaoUser findBy(String fieldName, Object value) throws TooManyResultsException {
        return null;
    }

    @Override
    public List<MiaoUser> findByIds(String ids) {
        return null;
    }

    @Override
    public List<MiaoUser> findByCondition(Condition condition) {
        return null;
    }

    @Override
    public List<MiaoUser> findAll() {
        return null;
    }

    @Override
    public Result register(MiaoUser user, HttpServletRequest request) {
        /*
        第一版
        MiaoUser miaoUserByEmail = miaoUserMapper.selectByEmail(user.getEmail());
        MiaoUser miaoUserByUserName = miaoUserMapper.selectByUserName(user.getUserName());
        if (miaoUserByEmail == null && miaoUserByUserName == null) {
            // 邮箱和账号 未被注册
            // 密码md5加密
            String key = user.getPassword();
            String md5Password = SecureUtil.md5(key);

            user.setPassword(md5Password);
            user.setCreateTime(DateUtil.date());
            user.setUpdateTime(DateUtil.date());
            user.setUserStatus(AdminConstant.USER_STATUS_LOCK);

            int i = miaoUserMapper.insertSelective(user);
            HttpSession session = request.getSession();

            // 在session中保存登录信息
            session.setAttribute("miao_user", user.getUserName());

            return i > 0 ? ResultGenerator.genSuccessResult() : ResultGenerator.genFailResult("喵呜~注册失败了");
        } else {
            // 邮箱或账号 已被注册
            return ResultGenerator.genFailResult("喵呜~邮箱或账号已经注册过了哦，直接去登录吧");
        }*/

        // Shiro版
        MiaoUser userByUserName = miaoUserMapper.selectByUserName(user.getUserName());
        if (userByUserName != null) {
            // 账号已注册过
            return ResultGenerator.genFailResult("喵呜~账号已经注册过了，直接去登录吧");
        }

        // 盐值
        String salt = String.valueOf(RandomUtil.randomInt(1, 6));

        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5", user.getPassword(), ByteSource.Util.bytes(salt), 1).toHex();
        user.setPassword(newPs);
        user.setSalt(salt);
        user.setCreateTime(DateUtil.date());
        user.setUpdateTime(DateUtil.date());
        user.setUserStatus(AdminConstant.USER_STATUS_LOCK);

        int i = miaoUserMapper.insertSelective(user);

        return i > 0 ? ResultGenerator.genSuccessResult() : ResultGenerator.genFailResult("喵呜~注册失败了");
    }

    @Override
    public Result login(String username, String password, HttpServletRequest request) {
        /*
        // 第一版
        // 查询是否有该用户(邮箱)
        MiaoUser user = miaoUserMapper.selectByEmail(keyword);
        if (user == null) {
            user = miaoUserMapper.selectByUserName(keyword);
            if (user == null) {
                return ResultGenerator.genFailResult("请注册后再登录~");
            }
        }

        // 密码解密后校验是否正确
        String md5Password = SecureUtil.md5(password);

        if (md5Password.equals(user.getPassword())) {
            HttpSession session = request.getSession();

            // 在session中保存登录信息
            session.setAttribute("miao_user", user.getUserName());
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("密码错误。。。");*/
        UsernamePasswordToken token = new UsernamePasswordToken(username,password); // TODO remeberMe

        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("登录失败，用户名[{}]", username, e);
            token.clear();
            return ResultGenerator.genFailResult("密码错误。。。");
        }
    }

    @Override
    public MiaoUser findByUserName(String userName) {
        return miaoUserMapper.selectByUserName(userName);
    }

}












