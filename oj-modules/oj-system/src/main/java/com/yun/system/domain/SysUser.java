package com.yun.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.PrimitiveIterator;

/**
 * @author yun
 * @date 2024/10/14 20:59
 * @desciption: 管理员实体类
 */
@Data
@TableName("tb_sys_user")
public class SysUser {
    private Long userId;
    private String userAccount;
    private String password;
    private String nickName;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;

}
