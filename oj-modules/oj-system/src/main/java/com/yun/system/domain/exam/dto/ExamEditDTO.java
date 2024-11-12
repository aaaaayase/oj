package com.yun.system.domain.exam.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/11 21:13
 * @desciption: 编辑竞赛内容传递参数类
 */
@Setter
@Getter
public class ExamEditDTO extends ExamAddDTO {

    private Long examId;

}
