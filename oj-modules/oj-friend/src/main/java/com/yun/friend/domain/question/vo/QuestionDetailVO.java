package com.yun.friend.domain.question.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/18 22:47
 * @desciption: 返回给前端的题目详细信息参数类
 */
@Setter
@Getter
public class QuestionDetailVO extends QuestionVO {

    private Long timeLimit;

    private Long spaceLimit;

    private String content;
    
    private String defaultCode;

}
