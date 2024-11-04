package com.yun.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yun
 * @date 2024/10/3 10:01
 * @desciption: 启动类
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OjGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OjGatewayApplication.class, args);
    }

}
