package com.fanrito.fanrpc.server;

import io.vertx.core.Vertx;

/**
 * Vertx HTTP 服务器
 */
public class VertxHttpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建Vertx实例
        Vertx vertx = Vertx.vertx();

        // 创建HTTP服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动HTTP服务器监听并指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to launch server: " + result.cause());
            }
        });

    }
}
