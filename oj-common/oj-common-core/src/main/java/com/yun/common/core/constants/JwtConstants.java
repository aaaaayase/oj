package com.yun.common.core.constants;

/**
 * @author yun
 * @date 2024/10/26 10:44
 * @desciption: jwt中用到的常量值
 */
public class JwtConstants {
    public static final String LOGIN_USER_ID = "userId"; // 用于生成jwttoken的key值 对应value是用户对象的id
    public static final String LOGIN_USER_KEY = "userKey";// 用于生成jwttoken的key值 对应value是生成的要存入redis中唯一的UUID
}
