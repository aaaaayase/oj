package com.yun.common.file.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.ClientException;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yun
 * @date 2024/11/18 18:23
 * @desciption:
 */
@Slf4j
@Configuration
public class OSSConfig {
    @Autowired
    private OSSProperties prop;

    public OSS ossClient;

    @Bean
    public OSS ossClient() throws ClientException {
        // 凭证提供 阿里云的认证信息
        DefaultCredentialProvider credentialsProvider =
                CredentialsProviderFactory.newDefaultCredentialProvider(prop.getAccessKeyId(), prop.getAccessKeySecret());
        // 创建ClientBuilderConfiguration
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        // 使⽤内⽹endpoint进⾏上传 构建客户端
        ossClient = OSSClientBuilder.create()
                .endpoint(prop.getEndpoint())
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(prop.getRegion())
                .build();
        return ossClient;
    }

    @PreDestroy
    public void closeOSSClient() {
        ossClient.shutdown();
    }
}

