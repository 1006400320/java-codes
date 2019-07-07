package com.linhuanjie.admin.dao;

import com.linhuanjie.admin.model.MiaoUser;
import com.linhuanjie.admin.model.MiaoUserKey;

public interface MiaoUserMapper {
    /**
     * Description:  根据主键删除
     *
     * @param  key key
     * @return deleteByPrimaryKey 的结果.
     * @mbg.generated
     */
    int deleteByPrimaryKey(MiaoUserKey key);

    /**
     * Description:  插入一条记录
     *
     * @param  record record
     * @return insert 的结果.
     * @mbg.generated
     */
    int insert(MiaoUser record);

    /**
     * Description:  插入一条记录, 实现选择入库
     *
     * @param  record record
     * @return insertSelective 的结果.
     * @mbg.generated
     */
    int insertSelective(MiaoUser record);

    /**
     * Description:  根据主键查询返回数据
     *
     * @param  key key
     * @return selectByPrimaryKey 的结果.
     * @mbg.generated
     */
    MiaoUser selectByPrimaryKey(MiaoUserKey key);

    /**
     * 根据email查询返回数据
     * @param email
     * @return
     */
     MiaoUser selectByEmail(String email);


    /**
     * Description:  根据主键更新数据, 可选择
     *
     * @param  record record
     * @return updateByPrimaryKeySelective 的结果.
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MiaoUser record);

    /**
     * Description:  根据主键更新数据
     *
     * @param  record record
     * @return updateByPrimaryKey 的结果.
     * @mbg.generated
     */
    int updateByPrimaryKey(MiaoUser record);
}