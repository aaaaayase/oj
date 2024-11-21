package com.yun.friend.service.user;

import com.yun.api.domain.vo.UserQuestionResultVO;
import com.yun.common.core.domain.R;
import com.yun.friend.domain.user.dto.UserSubmitDTO;

/**
 * @author yun
 * @date 2024/11/19 19:24
 * @desciption: 代码提交相关接口
 */
public interface IUserQuestionService {


    R<UserQuestionResultVO> submit(UserSubmitDTO submitDTO);

    boolean rabbitSubmit(UserSubmitDTO submitDTO);

    UserQuestionResultVO exeResult(Long examId, Long questionId, String currentTime);
}
