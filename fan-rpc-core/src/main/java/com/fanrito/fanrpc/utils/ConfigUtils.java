package com.fanrito.fanrpc.utils;


import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.fanrito.fanrpc.config.RpcConfig;
import com.fanrito.fanrpc.constant.RpcConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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
     * 加载配置文件(需要指定格式，支持properties和yml)
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String format) {
        return loadConfig(tClass, prefix, "", format);
    }

    /**
     * 加载配置文件(需要指定环境和格式，支持properties和yml)
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
        if (format.equals("properties")) {
            Props props = new Props(configFileBuffer.toString());
            return props.toBean(tClass, prefix);
        } else if (format.equals("yml") || format.equals("yaml")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            // 读取 YAML 文件
            try {
                Map<String, Object> data = mapper.readValue(ConfigUtils.class.getClassLoader().getResource(configFileBuffer.toString()), Map.class);
                // 处理 YAML 数据
                System.out.println(data);
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    String key = entry.getKey();
                    if (key.startsWith(prefix)) {
                        // 提取对象并转换为 RpcConfig 类
                        Object value = entry.getValue();
                        if (value instanceof Map) {
                            Map<String, Object> configData = (Map<String, Object>) value;
                            T config = mapper.convertValue(configData, tClass);
                            if (config != null) {
                                return config;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
