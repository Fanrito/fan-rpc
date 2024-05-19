package com.fanrito.fanrpc.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册配置类
 */
@Data
@NoArgsConstructor
public class RegistryConfig {

    /**
     * 注册中心类别
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://47.115.221.190:2379";

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（毫秒）
     */
    private Long timeout;
}
