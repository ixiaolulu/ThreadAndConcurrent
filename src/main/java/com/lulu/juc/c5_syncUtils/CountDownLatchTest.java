package com.lulu.juc.c5_syncUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-07 20:37
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        testCountDownLatch();

        testJoin();
    }

    private static void testJoin() {

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = 0;
                    for (int j = 0; j < 10000; j++) result += j;
                    System.out.println("join:" + result);
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

        System.out.println("end latch...");

    }

    private static void testCountDownLatch() {

        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = 0;
                    for (int j = 0; j < 10000; j++) result += j;
                    latch.countDown();
                    System.out.println("countDownLatch:" + result);

                }
            });
        }

        for (Thread t : threads) t.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch...");

    }
}
