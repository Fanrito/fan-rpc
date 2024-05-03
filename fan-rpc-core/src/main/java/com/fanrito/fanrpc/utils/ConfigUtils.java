package com.fanrito.fanrpc.utils;


import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.fanrito.fanrpc.constant.RpcConstant;

/**
 * 配置工具类
 */
public class ConfigUtils {

    /**
     * 加载配置文件
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "", RpcConstant.DEFAULT_CONFIG_FORMAT);
    }

    /**
     * 加载配置文件(支持多种环境)
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment, String format) {
        StringBuilder configFileBuffer = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuffer.append("-").append(environment);
        }
        configFileBuffer.append("." + format);
        Props props = new Props(configFileBuffer.toString());
        return props.toBean(tClass, prefix);
    }
}
