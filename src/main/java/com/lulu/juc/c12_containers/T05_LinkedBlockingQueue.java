package com.lulu.juc.c12_containers;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 19:38
 */
public class T05_LinkedBlockingQueue {

    static BlockingQueue<String> queue = new LinkedBlockingQueue();

    public static void main(String[] args) {

        Random r = new Random();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put("a" + r.nextInt(10000));//put阻塞方法
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            for (; ; ) {
                try {
                    System.out.println(Thread.currentThread().getName() + "take -" + queue.take());//take阻塞方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
