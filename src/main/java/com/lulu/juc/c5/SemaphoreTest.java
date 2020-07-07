package com.lulu.juc.c5;

import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-07 21:46
 */
public class SemaphoreTest {


    public static void main(String[] args) {
        //允许一个线程同时执行
//        Semaphore s = new Semaphore(1);
//        Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2,true);

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T1 running...");
                Thread.sleep(1000);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T2 running...");
                System.out.println("T2 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();    }

}
