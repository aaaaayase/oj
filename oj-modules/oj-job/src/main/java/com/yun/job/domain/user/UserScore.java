package com.yun.job.domain.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/22 9:10
 * @desciption: 用户得分
 */
@Setter
@Getter
public class UserScore {

    private Long examId;
    private Long userId;
    private Integer score;
    private int examRank;

}
