package com.yun.friend.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/19 14:47
 * @desciption: 用户提交代码从前端传递的参数类
 */
@Setter
@Getter
public class UserSubmitDTO {
    private Long examId;

    private Long questionId;

    private Integer programType; // 0代表java 1代表cpp 2代表go

    private String userCode;


}
