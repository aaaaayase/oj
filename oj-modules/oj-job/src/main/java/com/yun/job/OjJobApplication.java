package com.yun.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yun
 * @date 2024/10/3 10:01
 * @desciption: 启动类
 */

@SpringBootApplication
@MapperScan("com.yun.**.mapper")
public class OjJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(OjJobApplication.class, args);
    }

}
