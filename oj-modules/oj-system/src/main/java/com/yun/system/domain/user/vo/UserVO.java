package com.yun.system.domain.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/13 11:42
 * @desciption: 返回给前端的参数类
 */
@Setter
@Getter
public class UserVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String nickName;

    private Integer sex;

    private String phone;

    private String email;

    private String wechat;

    private String schoolName;

    private String majorName;

    private String introduce;

    private Integer status;
}
