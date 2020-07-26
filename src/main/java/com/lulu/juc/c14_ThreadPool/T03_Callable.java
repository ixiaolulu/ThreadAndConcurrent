package com.lulu.juc.c14_ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 认识Callable，对Runnable进行了扩展
 * 对Callable的调用，可以有返回值
 * @Author: Milo
 * @Date: 2020-07-25 11:57
 */
public class T03_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "hello callable";
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(callable);//异步
        String s = future.get();//阻塞方法

        System.out.println(s);
        executorService.shutdown();
    }
}
