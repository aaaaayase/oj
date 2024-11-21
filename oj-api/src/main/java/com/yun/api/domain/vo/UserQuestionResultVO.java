package com.yun.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yun.api.domain.UserExeResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/19 20:16
 * @desciption: 返回给前端的判题结果参数类
 */
@Getter
@Setter
public class UserQuestionResultVO {
    //是够通过标识
    private Integer pass; // 0  未通过  1 通过

    private String exeMessage; //异常信息

    private List<UserExeResult> userExeResultList;

    @JsonIgnore
    private Integer score;
}
