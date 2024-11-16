package com.yun.common.core.constants;

/**
 * @author yun
 * @date 2024/10/26 10:30
 * @desciption: 缓存相关常量
 */
public class CacheConstants {
    public static final String LOGIN_TOKEN_KEY = "logintoken:";

    public static final long EXP = 720;

    public static final long REFRESH_TIME = 30;

    public static final String PHONE_CODE_KEY = "p:c:";
    public static final String CODE_TIME_KEY = "c:t:";

    public static final String EXAM_UNFINISHED_lIST="e:t:l"; //未完竞赛列表
    public static final String EXAM_HISTORY_lIST="e:h:l"; // 历史竞赛列表
    public static final String EXAM_DETAIL="e:d:"; // 竞赛信息
}
