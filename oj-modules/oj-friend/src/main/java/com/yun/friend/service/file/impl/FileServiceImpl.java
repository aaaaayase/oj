package com.yun.friend.service.file.impl;

import com.yun.common.core.enums.ResultCode;
import com.yun.common.file.domain.OSSResult;
import com.yun.common.file.service.OSSService;
import com.yun.common.security.exception.ServiceException;
import com.yun.friend.service.file.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yun
 * @date 2024/11/18 17:55
 * @desciption: 文件上传相关业务逻辑
 */
@Service
@Slf4j
public class FileServiceImpl implements IFileService {
    @Autowired
    private OSSService ossService;

    @Override
    public OSSResult upload(MultipartFile file) {
        try {
            return ossService.uploadFile(file);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(ResultCode.FAILED_FILE_UPLOAD);
        }
    }
}
