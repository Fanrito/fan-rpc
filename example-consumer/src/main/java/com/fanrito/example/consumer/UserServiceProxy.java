package com.fanrito.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fanrito.example.common.models.User;
import com.fanrito.example.common.service.UserService;
import com.fanrito.fanrpc.model.RpcRequest;
import com.fanrito.fanrpc.model.RpcResponse;
import com.fanrito.fanrpc.serializer.JdkSerializer;

import java.io.IOException;

/**
 * 静态代理
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        JdkSerializer serializer = new JdkSerializer();

        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[] {User.class})
                .args(new Object[] {user})
                .build();

        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
