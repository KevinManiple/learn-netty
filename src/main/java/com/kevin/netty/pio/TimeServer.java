package com.kevin.netty.pio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.kevin.netty.bio.TimeServerHandler;

public class TimeServer {
    
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
            }
        }
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            Socket client = null;
            TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(50, 1000);
            while (true) {
                client = ss.accept();
                executePool.execute(new TimeServerHandler(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
                ss = null;
            } catch (IOException e) {
            }
        }
    }
}
