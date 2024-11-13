package com.yun.system.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/13 15:24
 * @desciption: 更新用户状态传递给后端参数类
 */
@Setter
@Getter
public class UserDTO {
    private Long userId;
    private Integer status;
}
