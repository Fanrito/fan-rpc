package com.fanrito.example.producer;

import com.fanrito.example.common.service.UserService;
import com.fanrito.fanrpc.registry.LocalRegistry;
import com.fanrito.fanrpc.server.HttpServer;
import com.fanrito.fanrpc.server.VertxHttpServer;

/**
 * 简易服务提供实例
 */
public class EasyProducerExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
