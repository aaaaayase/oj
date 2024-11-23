package com.yun.friend.service.user;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.common.core.domain.dto.PageQueryDTO;

/**
 * @author yun
 * @date 2024/11/22 13:08
 * @desciption:
 */
public interface IUserMessageService {
    TableDataInfo list(PageQueryDTO dto);
}
