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

    public static final String EXAM_UNFINISHED_lIST = "e:t:l"; //未完竞赛列表
    public static final String EXAM_HISTORY_lIST = "e:h:l"; // 历史竞赛列表
    public static final String EXAM_DETAIL = "e:d:"; // 竞赛信息

    public static final String USER_EXAM_LIST = "u:e:l:"; // 用户竞赛列表即用户报名的竞赛

    public static final String USER_DETAIL = "u:d:"; // 用户信息

    public static final long USER_EXP = 10; //

    public static final String USER_UPLOAD_TIMES_KEY = "u:u:t";
    public static final String QUESTION_lIST = "q:l";
    public static final String EXAM_QUESTION_lIST = "e:q:l:";

    public static final String USER_MESSAGE_LIST = "u:m:l:";
    public static final String MESSAGE_DETAIL = "m:d:";

    public static final String EXAM_RANK_LIST = "e:r:l:";


}
