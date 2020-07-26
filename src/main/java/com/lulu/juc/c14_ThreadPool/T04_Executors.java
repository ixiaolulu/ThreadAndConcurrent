package com.lulu.juc.c14_ThreadPool;

import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-25 12:18
 */
public class T04_Executors {
    public static void main(String[] args) {
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(1);
        Executors.newScheduledThreadPool(1);
    }
}
