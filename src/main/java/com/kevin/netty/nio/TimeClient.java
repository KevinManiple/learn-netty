package com.kevin.netty.nio;

public class TimeClient {
    
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                // 使用默认端口
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}
