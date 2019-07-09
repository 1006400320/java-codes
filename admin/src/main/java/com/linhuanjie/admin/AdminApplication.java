package com.linhuanjie.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = "com.linhuanjie.admin.dao")
public class AdminApplication extends SpringBootServletInitializer {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        logger.info("=====================java-codes-admin服务启动成功！====================");
        logger.info("==========温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行==========");
    }

    /**
     *重写configure
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AdminApplication.class);
    }
}
