package com.yun.friend.domain.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yun
 * @date 2024/11/10 19:41
 * @desciption:
 */
@Setter
@Getter
public class ExamRankVO {

    private Long userId;
    private String nickName;
    private int examRank;
    private int score;

}
