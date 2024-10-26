package com.yun.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yun.common.core.domain.BaseEntity;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.PrimitiveIterator;

/**
 * @author yun
 * @date 2024/10/14 20:59
 * @desciption: 管理员实体类
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_sys_user")
public class SysUser extends BaseEntity { 
    @TableId(value = "USER_ID", type = IdType.ASSIGN_ID)
    private Long userId;
    private String userAccount;
    private String password;
    private String nickName;

}
