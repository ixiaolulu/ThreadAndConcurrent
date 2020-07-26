package com.lulu.juc.c14_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 认识ExecutorService, 阅读API文档
 * 认识submit方法，扩展了execute方法，具有一个返回值
 * @Author: Milo
 * @Date: 2020-07-25 11:17
 */
public class T02_ExecutorService {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(() -> System.out.println("12123"));
        Future<String> futureRun = executorService.submit(() -> System.out.println("runnable with result"),"runnable with result");

        Future<String> futureCall = executorService.submit(() -> "abc123");

        try {
            System.out.println(future.get());
            System.out.println(futureRun.get());
            System.out.println(futureCall.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
    }
}
