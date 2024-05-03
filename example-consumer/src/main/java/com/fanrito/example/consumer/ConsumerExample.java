package com.fanrito.example.consumer;

import com.fanrito.example.common.models.User;
import com.fanrito.example.common.service.UserService;
import com.fanrito.fanrpc.config.RpcConfig;
import com.fanrito.fanrpc.proxy.ServiceProxyFactory;
import com.fanrito.fanrpc.utils.ConfigUtils;

public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "rpc", "yml");
        UserService userService = ServiceProxyFactory.getProxy(UserService.class, rpcConfig);
        User user = new User();
        user.setName("fanrito");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
