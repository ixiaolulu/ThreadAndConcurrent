package com.lulu.juc.c11_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 14:56
 */
public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("ticket num：" + i);
        }
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String ticket = tickets.poll();
                    if (ticket == null) break;
                    System.out.println("sell " + ticket);
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tickets.size());
    }
}
