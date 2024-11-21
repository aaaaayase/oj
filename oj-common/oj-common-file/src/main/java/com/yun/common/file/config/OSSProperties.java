package com.yun.common.file.config;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yun
 * @date 2024/11/18 18:24
 * @desciption:
 */
@Data
@Component
@ConfigurationProperties(prefix = "file.oss")
public class OSSProperties {
    private String endpoint;
    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String pathPrefix;
}