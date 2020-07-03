package com.lulu.juc.c3;

import java.util.concurrent.TimeUnit;

/**
 * @Description: * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 * @Author: Milo
 * @Date: 2020-07-03 21:59
 */
public class VolatileReference {

    boolean running = true;
    volatile static VolatileReference V = new VolatileReference();

    void m() {
        System.out.println("m start...");
        while (running) {

        }
        System.out.println("m end...");
    }

    public static void main(String[] args) {
        new Thread(V::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        V.running = true;
    }
}
