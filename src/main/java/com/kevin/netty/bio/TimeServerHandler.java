package com.kevin.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeServerHandler implements Runnable {
    
    private Socket client;
    
    public TimeServerHandler(Socket client) {
        this.client = client;
    }
    
    public Socket getClient() {
        return client;
    }
    
    public void setClient(Socket client) {
        this.client = client;
    }
    
    @Override
    public void run() {
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            pw = new PrintWriter(this.client.getOutputStream(), true);
            pw.println("Hello client!");
            br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != pw) {
                pw.close();
                pw = null;
            }
            try {
                if (null != br) {
                    br.close();
                    br = null;
                }
            } catch (IOException e) {
            }
            try {
                if (null != this.client) {
                    this.client.close();
                    this.client = null;
                }
            } catch (IOException e) {
            }
        }
    }
}
