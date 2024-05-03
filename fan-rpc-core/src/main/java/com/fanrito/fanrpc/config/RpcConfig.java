package com.fanrito.fanrpc.config;

import lombok.Data;

/**
 * RPC 配置类
 */
@Data
public class RpcConfig {

    /**
     * RPC 服务名称
     */
    private String name = "fanrpc";

    /**
     * RPC 服务版本
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String address = "localhost";

    /**
     * 服务器端口
     */
    private Integer port = 8080;
}
