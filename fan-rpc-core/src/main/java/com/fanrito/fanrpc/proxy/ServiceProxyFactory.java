package com.fanrito.fanrpc.proxy;

import com.fanrito.fanrpc.config.RpcConfig;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass, RpcConfig rpcConfig) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[] {serviceClass},
                new ServiceProxy(rpcConfig)
        );
    }
}
