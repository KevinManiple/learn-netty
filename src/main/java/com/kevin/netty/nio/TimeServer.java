package com.kevin.netty.nio;

public class TimeServer {
    
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                // 使用默认端口
            }
        }
        new Thread(new MultiplexerTimeServer(port), "NIO-MultiplexerTimeServer-001").start();
    }
}
