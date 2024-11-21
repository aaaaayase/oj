package com.yun.friend.domain.user.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/18 15:17
 * @desciption: 编辑用户个人详细信息传递的参数类
 */
@Setter
@Getter
public class UserUpdateDTO {
    private String headImage;

    private String nickName;

    private Integer sex;

    private String phone;

    private String email;

    private String wechat;

    private String schoolName;

    private String majorName;

    private String introduce;
}
