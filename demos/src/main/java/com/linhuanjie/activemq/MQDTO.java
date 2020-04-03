package com.linhuanjie.activemq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020.04.03 22:56
 **/
@Data
@AllArgsConstructor
public class MQDTO implements Serializable {

    private String username;
    private String password;

}
