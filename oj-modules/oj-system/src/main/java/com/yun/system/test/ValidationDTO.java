package com.yun.system.test;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author yun
 * @date 2024/11/4 14:35
 * @desciption: test
 */
@Getter
@Setter
public class ValidationDTO {
    @NotBlank(message = "⽤⼾账号不能为空")
    private String userAccount;
    @NotBlank(message = "⽤⼾密码不能为空")
    @Size(min = 5, max = 10, message = "密码⻓度不能少于6位，不能⼤于10位")
    private String password;
    @Min(value = 0, message = "年龄不能⼩于0岁")
    @Max(value = 60, message = "年龄不能⼤于60岁")
    private int age;
    @Email(message = "必须符合邮箱格式")
    private String email;
    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", message = "⼿机号码格式不正确")
    private String phone;
    @Past(message = "开始⽇期必须是过去的⽇期")
    private LocalDate startDate;
    @Future(message = "结束⽇期必须是未来的⽇期")
    private LocalDate endDate;
}