package com.fanrito.fanrpc.proxy;

import com.fanrito.fanrpc.config.RpcConfig;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass, RpcConfig rpcConfig) {
        if (rpcConfig.getMock()) {
            return (T) Proxy.newProxyInstance(
                    serviceClass.getClassLoader(),
                    new Class[]{serviceClass},
                    new MockServiceProxy()
            );
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy(rpcConfig)
        );
    }

    /**
     * 获取mock代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy()
        );
    }
}
