package com.yun.friend.service.file;

import com.yun.common.file.domain.OSSResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yun
 * @date 2024/11/18 17:54
 * @desciption: 文件上传相关接口
 */
public interface IFileService {
    OSSResult upload(MultipartFile file);
}
