package com.kevin.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
            while (true) {
                client = ss.accept();
                new Thread(new TimeServerHandler(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ss) {
                    ss.close();
                    ss = null;
                }
            } catch (IOException e) {
            }
        }
    }
}
