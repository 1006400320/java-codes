package com.linhuanjie.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author: linhuanjie
 * @description:   用更优雅的方式发HTTP请求(RestTemplate详解)（https://www.cnblogs.com/javazhiyin/p/9851775.html）
 * @createTime : 2018-11-29 10:07
 * @email: lhuanjie@qq.com
 */

public class RestTemplateDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("ly.mama.cn/main/index", String.class);
        System.out.println("forObject = " + forObject);

        ResponseEntity<String> forEntity = restTemplate.getForEntity("ly.mama.cn/main/index", String.class);
        System.out.println("forEntity = " + forEntity);

    }

}
