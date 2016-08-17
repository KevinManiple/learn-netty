package com.kevin.netty.pio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
    
    private ExecutorService service;
    
    public ExecutorService getService() {
        return service;
    }
    
    public void setService(ExecutorService service) {
        this.service = service;
    }
    
    public TimeServerHandlerExecutePool(int maximumPoolSize, int capacity) {
        service = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maximumPoolSize, 100l,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(capacity), new ThreadFactory() {
                    
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                }, new RejectedExecutionHandler() {
                    
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(executor.getQueue().remainingCapacity());
                        System.out.println(executor.getActiveCount());
                    }
                });
    }
    
    public void execute(Runnable runnable) {
        this.service.execute(runnable);
    }
}
