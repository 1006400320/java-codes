package com.linhuanjie.admin.service.impl;

import com.linhuanjie.admin.dao.MiaoUserMapper;
import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.model.MiaoUserKey;
import com.linhuanjie.admin.service.UserService;
import com.linhuanjie.common.result.Result;
import com.linhuanjie.common.result.ResultGenerator;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-07-07 10:30
 * @email: lhuanjie@qq.com
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MiaoUserMapper mapper;

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
    public Result register(MiaoUser user) {
        MiaoUser miaoUser = mapper.selectByEmail(user.getEmail());
        if (miaoUser == null) {
            // 邮箱未注册
            int i = mapper.insertSelective(user);

            return i>0 ? ResultGenerator.genSuccessResult() : ResultGenerator.genFailResult("喵呜~注册失败了");
        } else {
            // 邮箱已注册
            return  ResultGenerator.genFailResult("喵呜~邮箱已经注册过了哦，直接去登录吧");
        }
    }


}












