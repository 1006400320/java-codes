package com.linhuanjie.relax;

import cn.hutool.core.util.RandomUtil;

/**
 * 闲着无聊，突发奇想。
 * 算了下王者荣耀，王者0星 --> 王者50星，保持50%的胜率，大概要打多少场。
 * 当然，目前这种计算不太合理，
 *      没有考虑各种活动、排位保护卡、荣耀积分的规则也比较简单粗暴
 *
 * @author : linhuanjie
 * @email : lhuanjie@qq.com
 * @create : 2020.05.13 22:40
 **/
public class KingGloryDemo {

    public static void calc() {
        // 初始星星数
        int stars = 0;
        // 目标：五十星
        int finalStars = 50;
        // 还有 60 天
        final int day = 60;
        // 上荣耀王者需要的场数
        int times = 0;
        // 输的场次
        int lostTimes = 0;
        // 赢的场次
        int winTimes = 0;
        // 荣耀积分：每打一局加10分，满200分则加一颗星，40分可以抵消输一局
        int score = 0;
        // 荣耀积分满了，加的星星数
        int scoreEarnStars = 0;
        // 荣耀积分抵消星星的场次
        int scoreOffset = 0;

        while (stars < finalStars) {
            if (RandomUtil.randomInt() % 2 == 0) {
//                System.out.println("排位输了QAQ");
                if (score >= 40) {
                    score -= 40;
                    scoreOffset++;
                } else {
                    stars--;
                }
                lostTimes++;
                score += 10;
            } else {
//                System.out.println("排位赢了TuT");
                stars++;
                winTimes++;
                score += 15;
            }
            if (score >= 200) {
                stars++;
                score = 0;
//                System.out.println("积分满了，白赚一颗星星TyT");
                scoreEarnStars++;
            }
            times++;
        }
        /**
         * 还有 荣耀积分可以抵消输一局
         */

        System.out.println("终于上荣耀王者了~~~打了 " + times + " 场排位，每天需要打" + times / 60 + "场排位。输了 " + lostTimes + " 场，赢了 " + winTimes + " 场。" +
                "荣耀积分帮我抵消了 " + scoreOffset + " 个星星，帮我攒了 " + scoreEarnStars + " 颗星星");

    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            calc();
        }
    }
}
