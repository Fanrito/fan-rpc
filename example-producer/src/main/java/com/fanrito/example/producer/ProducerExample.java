package com.fanrito.example.producer;

import com.fanrito.example.common.service.UserService;
import com.fanrito.fanrpc.RpcApplication;
import com.fanrito.fanrpc.config.RegistryConfig;
import com.fanrito.fanrpc.config.RpcConfig;
import com.fanrito.fanrpc.constant.RpcConstant;
import com.fanrito.fanrpc.model.ServiceMetaInfo;
import com.fanrito.fanrpc.registry.LocalRegistry;
import com.fanrito.fanrpc.registry.Registry;
import com.fanrito.fanrpc.registry.RegistryFactory;
import com.fanrito.fanrpc.server.HttpServer;
import com.fanrito.fanrpc.server.VertxHttpServer;
import com.fanrito.fanrpc.server.tcp.VertxTcpServer;
import com.fanrito.fanrpc.utils.ConfigUtils;

public class ProducerExample {

    public static void main(String[] args) {
// RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getAddress());
        serviceMetaInfo.setServicePort(rpcConfig.getPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 TCP 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(8081);
//        VertxHttpServer vertxHttpServer = new VertxHttpServer();
//        vertxHttpServer.doStart(8081);
    }
}
