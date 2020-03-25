package com.linhuanjie.spring;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 详解 RestTemplate 操作   https://blog.csdn.net/xmtblog/article/details/82761642
 * 用更优雅的方式发HTTP请求(RestTemplate详解)   https://www.cnblogs.com/javazhiyin/p/9851775.html
 * @author: linhuanjie
 * @createTime : 2018-11-29 10:07
 * @email: lhuanjie@qq.com
 */

public class RestTemplateDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://pms.api.xiaoshuxiong.com/pmsapi/check/info/all";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println("forObject = " + forObject);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println("forEntity = " + forEntity);
    }
}
