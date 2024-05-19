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
import com.fanrito.fanrpc.utils.ConfigUtils;

public class ProducerExample {

    public static void main(String[] args) {
        // RPC框架初始化
        RpcConfig newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX, "yml");

        RpcApplication.init(newRpcConfig);

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        RegistryConfig registryConfig = RpcApplication.getRpcConfig().getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();

        serviceMetaInfo.setServiceName(UserService.class.getName());
        serviceMetaInfo.setServiceVersion("1.0");
        serviceMetaInfo.setServiceHost(newRpcConfig.getAddress());
        serviceMetaInfo.setServicePort(newRpcConfig.getPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        System.out.println(RpcApplication.getRpcConfig());
        httpServer.doStart(RpcApplication.getRpcConfig().getPort());
    }
}
