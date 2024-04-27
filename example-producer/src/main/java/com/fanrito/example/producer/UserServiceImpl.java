package com.fanrito.example.producer;

import com.fanrito.example.common.models.User;
import com.fanrito.example.common.service.UserService;

/**
 * 用户服务实践类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
