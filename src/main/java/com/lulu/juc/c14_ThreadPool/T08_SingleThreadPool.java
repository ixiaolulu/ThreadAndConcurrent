package com.lulu.juc.c14_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-26 11:35
 */
public class T08_SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            final int j = i;
            service.execute(()->{

                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }


    }
}
