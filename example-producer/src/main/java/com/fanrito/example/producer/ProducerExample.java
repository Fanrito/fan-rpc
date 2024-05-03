package com.fanrito.example.producer;

import com.fanrito.example.common.service.UserService;
import com.fanrito.fanrpc.RpcApplication;
import com.fanrito.fanrpc.config.RpcConfig;
import com.fanrito.fanrpc.registry.LocalRegistry;
import com.fanrito.fanrpc.server.HttpServer;
import com.fanrito.fanrpc.server.VertxHttpServer;
import com.fanrito.fanrpc.utils.ConfigUtils;

public class ProducerExample {

    public static void main(String[] args) {
        // RPC框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        System.out.println(RpcApplication.getRpcConfig());
        httpServer.doStart(RpcApplication.getRpcConfig().getPort());
    }
}
