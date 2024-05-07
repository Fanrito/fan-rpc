package com.fanrito.example.consumer;

import com.fanrito.example.common.models.User;
import com.fanrito.example.common.service.UserService;
import com.fanrito.fanrpc.config.RpcConfig;
import com.fanrito.fanrpc.proxy.ServiceProxyFactory;
import com.fanrito.fanrpc.utils.ConfigUtils;

public class MockConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getMockProxy(UserService.class);
        User user = new User();
        user.setName("fanrito");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }
}
