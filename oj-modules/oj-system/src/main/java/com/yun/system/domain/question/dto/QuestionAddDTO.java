package com.yun.system.domain.question.dto;

import com.yun.common.core.domain.BaseEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/9 19:02
 * @desciption: 添加问题后端参数类
 */
@Setter
@Getter
public class QuestionAddDTO extends BaseEntity {
    @NotNull(message = "标题不能为空")
    private String title;
    @Min(value = 1, message = "难度不能小于简单")
    @Max(value = 3, message = "难度不能大于困难")
    private Integer difficulty;

    @NotNull(message = "时间限制不能为空")
    private Long timeLimit;

    @NotNull(message = "空间限制不能为空")
    private Long spaceLimit;

    @NotNull(message = "内容不能为空")
    private String content;

    private String questionCase;

    @NotNull(message = "默认代码块不能为空")
    private String defaultCode;

    @NotNull(message = "主函数不能为空")
    private String mainFuc;
}
