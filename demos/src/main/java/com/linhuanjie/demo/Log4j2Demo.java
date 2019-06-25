package com.linhuanjie.demo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-11-28 22:21
 * @email: lhuanjie@qq.com
 */
public class Log4j2Demo {

    private static final Logger log = LogManager.getLogger(Log4j2Demo.class);

    public static void main(String[] args) {
        log.info("trace_log...program begin");
        for (int i = 1; i <= 10; i++) {
            log.info("info_log...no:"+i);
        }
        log.info("end...");
    }
}
