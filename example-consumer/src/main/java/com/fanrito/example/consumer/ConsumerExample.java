package com.fanrito.example.consumer;

import com.fanrito.example.common.models.User;
import com.fanrito.example.common.service.UserService;
import com.fanrito.fanrpc.RpcApplication;
import com.fanrito.fanrpc.config.RpcConfig;
import com.fanrito.fanrpc.proxy.ServiceProxyFactory;
import com.fanrito.fanrpc.utils.ConfigUtils;

public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "rpc", "yml");
        RpcApplication.init(rpcConfig);
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("fanrito");
        // 计时开始
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 200; i++) {
            User newUser = userService.getUser(user);
            count++;
            System.out.println(count);
//            if (newUser != null) {
//                System.out.println(newUser.getName());
//            } else {
//                System.out.println("user == null");
//            }
        }
        // 计时结束
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + "ms");
        System.out.println("平均耗时：" + ((end - start) / count) + "ms");
//        int number = userService.getNumber();
//        System.out.println(number);
    }
}
