package com.yun.judge.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yun
 * @date 2024/11/20 10:21
 * @desciption: 容器池配置
 */
@Configuration
public class DockerSandBoxPoolConfig {

    @Value("${sandbox.docker.host:tcp://localhost:2375}")
    private String dockerHost;

    @Value("${sandbox.docker.image:openjdk:8-jdk-alpine}")
    private String sandboxImage;

    @Value("${sandbox.docker.volume:/usr/share/java}")
    private String volumeDir;

    @Value("${sandbox.limit.memory:100000000}")
    private Long memoryLimit;

    @Value("${sandbox.limit.memory-swap:100000000}")
    private Long memorySwapLimit;

    @Value("${sandbox.limit.cpu:1}")
    private Long cpuLimit;

    @Value("${sandbox.docker.pool.size:4}")
    private int poolSize;

    @Value("${sandbox.docker.name-prefix:oj-sandbox-jdk}")
    private String containerNamePrefix;


    // docker客户端
    @Bean
    public DockerClient createDockerClient() {
        DefaultDockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHost)
                .build();
        return DockerClientBuilder
                .getInstance(clientConfig)
                .withDockerCmdExecFactory(new NettyDockerCmdExecFactory())
                .build();
    }

    // 创建代码沙箱容器池
    @Bean
    public DockerSandBoxPool createDockerSandBoxPool(DockerClient dockerClient) {
        DockerSandBoxPool dockerSandBoxPool = new DockerSandBoxPool(dockerClient, sandboxImage, volumeDir, memoryLimit,
                memorySwapLimit, cpuLimit, poolSize, containerNamePrefix);
        dockerSandBoxPool.initDockerPool();
        return dockerSandBoxPool;
    }
}
