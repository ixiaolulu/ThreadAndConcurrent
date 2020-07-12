package com.lulu.juc.c7_AQS;

import java.util.concurrent.locks.Lock;

/**
 * @Description: 通过AQS实现自定义锁，目前仅实现了lock和unlock
 * @Author: Milo
 * @Date: 2020-07-11 12:18
 */
public class Main {

    public static int m = 0;

    private static Lock myLock = new MyLock();

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                try {
                    myLock.lock();
                    for (int j = 0; j < 100; j++) m++;
                } finally {
                    myLock.unlock();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(m);

    }
}
