package com.yun.friend.service.user;

import com.yun.common.core.domain.TableDataInfo;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;

/**
 * @author yun
 * @date 2024/11/17 9:59
 * @desciption:
 */
public interface IUserExamService {
    int enter(String token, Long examId);

    TableDataInfo list(ExamQueryDTO examQueryDTO);
}
