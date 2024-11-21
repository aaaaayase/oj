package com.yun.friend.domain.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/19 15:19
 * @desciption: 用户提交代码输出的结果
 */
@Getter
@Setter
public class UserExeResult {

    private String input;

    private String expectOutput;

    private String output;

}
