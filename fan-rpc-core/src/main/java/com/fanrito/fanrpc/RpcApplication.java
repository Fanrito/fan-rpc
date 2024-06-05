package com.fanrito.fanrpc;

import com.fanrito.fanrpc.config.RegistryConfig;
import com.fanrito.fanrpc.config.RpcConfig;
import com.fanrito.fanrpc.constant.RpcConstant;
import com.fanrito.fanrpc.registry.Registry;
import com.fanrito.fanrpc.registry.RegistryFactory;
import com.fanrito.fanrpc.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcApplication {

    private static final Logger log = LoggerFactory.getLogger(RpcApplication.class);
    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, rpcConfig:{}", rpcConfig.toString());

        // 初始化注册中心
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, registryConfig: {}", registryConfig);

        // JVM推出时执行销毁服务
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            log.warn("load config error, use default", e);
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig() {

        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}

