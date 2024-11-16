package com.yun.friend.test;

import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.message.service.AliSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yun
 * @date 2024/11/14 11:36
 * @desciption: 测试
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private AliSmsService aliSmsService;

    @GetMapping("/sendCode")
    public R<Void> sendCode(String phone, String code) {
        return toR(aliSmsService.sendMobileCode(phone,code));
    }

}
