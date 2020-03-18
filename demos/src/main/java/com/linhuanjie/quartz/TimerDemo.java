package com.linhuanjie.quartz;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019-10-17 10:28
 * @email: lhuanjie@qq.com
 */
public class TimerDemo {

    public static void main(String[] args) {
        System.out.println("start");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("test1");
            }
        }, 5000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("test2");
            }
        }, 10000);


    }
}
