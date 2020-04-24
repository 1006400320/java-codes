package com.linhuanjie.javase.serializable;

import com.linhuanjie.common.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020/4/24 14:52
 **/

public class SerializableUser implements Serializable {
    private static final long serialVersionUID = -4171074367034581463L;

    private Integer id;
    private String userName;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SerializableUser() {
    }

    public SerializableUser(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return JsonUtils.toString(this);
    }
}

