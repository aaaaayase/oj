package com.yun.friend.mapper.message;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.friend.domain.message.MessageText;
import com.yun.friend.domain.message.vo.MessageTextVO;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/22 10:27
 * @desciption:
 */
public interface IMessageTextMapper extends BaseMapper<MessageText> {
    List<MessageTextVO> selectUserMsgList(Long userId);
}
