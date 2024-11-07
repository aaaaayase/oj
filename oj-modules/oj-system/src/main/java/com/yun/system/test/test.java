package com.yun.system.test;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yun
 * @date 2024/11/4 14:33
 * @desciption: test
 */
@RestController
@RequestMapping("/test")
public class test {
    @GetMapping("/validation")
    public String validation(@Validated ValidationDTO validationDTO) {
        return "参数校验测试";
    }
}
