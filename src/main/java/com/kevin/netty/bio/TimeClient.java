package com.kevin.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
    
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", port);
            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            pw.println("Hello server!");
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(br.readLine());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != client) {
                    client.close();
                    client = null;
                }
            } catch (IOException e) {
            }
        }
    }
}
