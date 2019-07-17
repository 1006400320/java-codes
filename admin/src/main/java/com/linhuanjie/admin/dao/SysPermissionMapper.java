package com.linhuanjie.admin.dao;

import com.linhuanjie.admin.model.SysPermission;
import com.linhuanjie.admin.model.SysPermissionKey;

import java.util.List;

public interface SysPermissionMapper {
    /**
     * Description:  根据主键删除
     *
     * @param  key key
     * @return deleteByPrimaryKey 的结果.
     * @mbg.generated
     */
    int deleteByPrimaryKey(SysPermissionKey key);

    /**
     * Description:  插入一条记录
     *
     * @param  record record
     * @return insert 的结果.
     * @mbg.generated
     */
    int insert(SysPermission record);

    /**
     * Description:  插入一条记录, 实现选择入库
     *
     * @param  record record
     * @return insertSelective 的结果.
     * @mbg.generated
     */
    int insertSelective(SysPermission record);

    /**
     * Description:  根据主键查询返回数据
     *
     * @param  key key
     * @return selectByPrimaryKey 的结果.
     * @mbg.generated
     */
    SysPermission selectByPrimaryKey(SysPermissionKey key);



    /**
     * Description:  根据主键更新数据, 可选择
     *
     * @param  record record
     * @return updateByPrimaryKeySelective 的结果.
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysPermission record);

    /**
     * Description:  根据主键更新数据
     *
     * @param  record record
     * @return updateByPrimaryKey 的结果.
     * @mbg.generated
     */
    int updateByPrimaryKey(SysPermission record);
}