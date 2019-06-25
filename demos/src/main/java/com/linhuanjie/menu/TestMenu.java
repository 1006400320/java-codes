package com.linhuanjie.menu;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-11-27 21:06
 * @email: lhuanjie@qq.com
 */
public class TestMenu {
    /**
     * 初始化数据
     *
     * @return
     */
    public static List<Menu> initData() {
        List<Menu> treeData = new ArrayList<Menu>();
        treeData.add(new Menu("1", "1", "0"));
        treeData.add(new Menu("2", "2", "1"));
        treeData.add(new Menu("3", "3", "1"));
        treeData.add(new Menu("4", "4", "2"));
//        treeData.add(new menu("5", "5", "0"));
//        treeData.add(new menu("6", "6", "2"));
//        treeData.add(new menu("7", "7", "2"));
//        treeData.add(new menu("8", "8", "3"));
//        treeData.add(new menu("9", "9", "10"));
//        treeData.add(new menu("10", "10", "6"));
//        treeData.add(new menu("11", "11", "7"));
        return treeData;
    }

    /**
     * 子菜单递归
     *
     * @param id
     * @param rootMenu
     * @return
     */
    private static List<Menu> getChild(String id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {
            // 递归
            menu.setChildMenus(getChild(menu.getId(), rootMenu));
        }

        // 判断递归结束
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 原始数据
        List<Menu> treeData = initData();

        // 查看结果
        for (Menu menu : treeData) {
            System.out.println(menu.toString());
        }

        // 结果
        List<Menu> menuList = new ArrayList<Menu>();
        // 树递归

        // 先找到所有的一级菜单
        for (int i = 0; i < treeData.size(); i++) {
            // 一级菜单父ID为0
            if (treeData.get(i).getParentId().equals("0")) {
                menuList.add(treeData.get(i));
            }
        }

        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Menu menu : menuList) {
            menu.setChildMenus(getChild(menu.getId(), treeData));
        }

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("menu", menuList);
        System.out.println(JSON.toJSONString(jsonMap));

        buildMenu(menuList);
        System.out.println(str);
    }


    /**
     * 遍历菜单
     *
     * @param arr
     * @return
     */
    public static String str = "";

    public static String buildMenu(List<Menu> arr) {

        for (int i = 0; i < arr.size(); i++) {
            str += "<li>";
            str += "<a href='javascript:;'>" +
                    "<cite>" + arr.get(i).getName() + "</cite>" +
                    "<i class='iconfont nav_right'>" + arr.get(i).getIcon() + "</i>" +
                    "</a>";
            //存在子菜单 递归
            if (arr.get(i).getChildMenus() != null && arr.get(i).getChildMenus().size() > 0) {
                str += "<ul>";
                buildMenu(arr.get(i).getChildMenus()); // 递归
                str += "</ul>";
            }
            str += "</li>";
        }

        return str;
    }


}
