package com.lulu.juc.c11_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 有N张火车票，每张票都有一个编号 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 * @Author: Milo
 * @Date: 2020-07-18 14:41
 */

public class TicketSeller1 {

    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("ticket num：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("sell " + tickets.remove(0));
                }
            }).start();
        }
    }
}
