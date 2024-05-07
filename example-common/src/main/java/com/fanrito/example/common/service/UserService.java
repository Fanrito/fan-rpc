package com.fanrito.example.common.service;

import com.fanrito.example.common.models.User;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 新方法 - 获取数字
     * @return
     */
    default int getNumber() {

        return 1;
    }
}
