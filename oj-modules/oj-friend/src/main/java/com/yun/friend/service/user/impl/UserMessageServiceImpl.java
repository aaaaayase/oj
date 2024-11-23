package com.yun.friend.service.user.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.common.core.constants.Constants;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.common.core.domain.dto.PageQueryDTO;
import com.yun.common.core.enums.ExamListType;
import com.yun.common.core.utils.ThreadLocalUtil;
import com.yun.friend.domain.exam.vo.ExamVO;
import com.yun.friend.domain.message.vo.MessageTextVO;
import com.yun.friend.manager.MessageCacheManager;
import com.yun.friend.mapper.message.IMessageTextMapper;
import com.yun.friend.mapper.user.IUserSubmitMapper;
import com.yun.friend.service.user.IUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun
 * @date 2024/11/22 13:08
 * @desciption:
 */
@Service
public class UserMessageServiceImpl implements IUserMessageService {

    @Autowired
    private MessageCacheManager messageCacheManager;

    @Autowired
    private IMessageTextMapper messageTextMapper;

    @Override
    public TableDataInfo list(PageQueryDTO dto) {
        Long userId = ThreadLocalUtil.get(Constants.USER_ID, Long.class);
        Long total = messageCacheManager.getListSize(userId);
        List<MessageTextVO> messageTextVOList;
        if (total == null || total <= 0) {
            // 从数据库中查询并且同步到缓存
            PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
            messageTextVOList = messageTextMapper.selectUserMsgList(userId);
            messageCacheManager.refreshCache(userId);
            total = new PageInfo<>(messageTextVOList).getTotal();
        } else {
            messageTextVOList = messageCacheManager.getMsgTextVOList(dto, userId);
        }
        if (CollectionUtil.isEmpty(messageTextVOList)) {
            return TableDataInfo.empty();
        }
        return TableDataInfo.success(messageTextVOList, total);
    }
}
