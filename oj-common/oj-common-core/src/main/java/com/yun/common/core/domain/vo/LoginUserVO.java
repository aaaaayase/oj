package com.yun.common.core.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/7 15:19
 * @desciption: 传给前端的登录者信息
 */
@Setter
@Getter
public class LoginUserVO {
    private String nickName;

    private String headImage; // 头像

}
