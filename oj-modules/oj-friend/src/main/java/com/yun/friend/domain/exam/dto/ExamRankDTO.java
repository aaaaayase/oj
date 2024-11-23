package com.yun.friend.domain.exam.dto;

import com.yun.common.core.domain.dto.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yun
 * @date 2024/11/17 9:57
 * @desciption:
 */
@Setter
@Getter
public class ExamRankDTO extends PageQueryDTO {

    private Long examId;
}
