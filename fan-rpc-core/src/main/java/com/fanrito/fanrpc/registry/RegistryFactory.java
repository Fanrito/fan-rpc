package com.fanrito.fanrpc.registry;

import com.fanrito.fanrpc.config.RegistryConfig;
import com.fanrito.fanrpc.spi.SpiLoader;

public class RegistryFactory {
    
    static {
        SpiLoader.load(Registry.class);
    }

    private static final Registry DEFUALT_REGISTRY = new EtcdRegistry();

    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
