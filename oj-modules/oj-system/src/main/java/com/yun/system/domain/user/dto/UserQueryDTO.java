package com.yun.system.domain.user.dto;

import com.yun.common.core.domain.dto.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/13 11:38
 * @desciption: 用户查询传递给后端参数类
 */
@Setter
@Getter
public class UserQueryDTO extends PageQueryDTO {

    private Long userId;

    private String nickName;

}
