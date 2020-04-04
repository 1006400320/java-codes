package com.linhuanjie.admin.system.controller;

import com.linhuanjie.admin.temp.GoodsVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019.07.15 21:19
 * @email: lhuanjie@qq.com
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @GetMapping("/list")
    public  Map<String, Object> goodsList() {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", 1000);

        List<GoodsVO> goodsList = new ArrayList<>();
        GoodsVO vo = null;
        for (int i = 0; i < 30; i++) {
            vo = new GoodsVO();
//            vo.setId(1000 + i);
//            vo.setUsername("user" + i);
//            vo.setSex("女");
//            vo.setCity("广州" + i);
//            vo.setSign("签名" + i);
//            vo.setExperience("255");
//            vo.setLogins("24");
//            vo.setWealth("6298078");
//            vo.setClassify("作家");
//            vo.setScore("99");

            goodsList.add(vo);
        }

        resultMap.put("data",goodsList);

        return resultMap;
    }

}
