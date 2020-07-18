package com.lulu.juc.c11_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 14:54
 */
public class TicketSeller3 {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("ticket num：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (tickets){
                    while (tickets.size() > 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("sell " + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
