package com.yun.judge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yun
 * @date 2024/10/3 10:01
 * @desciption: 启动类
 */

@SpringBootApplication
@MapperScan("com.yun.**.mapper")
public class OjJudgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OjJudgeApplication.class, args);
    }

}
