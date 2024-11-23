package com.yun.friend.controller.user;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.yun.common.core.constants.HttpConstants;
import com.yun.common.core.controller.BaseController;
import com.yun.common.core.domain.R;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.common.core.domain.dto.PageQueryDTO;
import com.yun.common.core.domain.vo.LoginUserVO;
import com.yun.friend.domain.user.dto.UserDTO;
import com.yun.friend.domain.user.dto.UserUpdateDTO;
import com.yun.friend.domain.user.vo.UserVO;
import com.yun.friend.service.user.IUserMessageService;
import com.yun.friend.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yun
 * @date 2024/11/13 11:24
 * @desciption: 客户端用户相关接口
 */
@Tag(name = "消息相关接口")
@RestController
@RequestMapping("/user/message")
public class UserMessageController extends BaseController {
    @Autowired
    private IUserMessageService userMessageService;

    @GetMapping("/list")
    public TableDataInfo list(PageQueryDTO dto) {
        return userMessageService.list(dto);
    }

}
