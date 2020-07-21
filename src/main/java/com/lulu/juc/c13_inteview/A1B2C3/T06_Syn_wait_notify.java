/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.juc.c13_inteview.A1B2C3;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @since 2020/7/21 15:47
 * @author Milo.Ding
 *
 */
public class T06_Syn_wait_notify {

    private static volatile boolean t1Started = false;
//    static CountDownLatch latch =  new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();

        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : b) {
                    System.out.print(c);
                    t1Started = true;
//                    latch.countDown();
                    try {
                        o.notify();
                        o.wait();// 会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须，否则无法停止程序
            }
        }, "t1").start();

        new Thread(() -> {
//            try {
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (o) {
                while(!t1Started){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : a) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }
}
