package com.lulu.juc.c12_containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 * @Author: Milo
 * @Date: 2020-07-18 19:15
 */
public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        List<String> list =
//                new ArrayList<>();
//                new Vector<>();
                new CopyOnWriteArrayList<>();//

        Random r = new Random();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) list.add("a" + r.nextInt(100000));
                }
            };
            threads[i] = new Thread(runnable);
        }

        runAndComputeTime(threads);

        System.out.println(list.size());
    }

    private static void runAndComputeTime(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.stream(threads).forEach(thread -> thread.start());
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(System.currentTimeMillis() - start);
    }
}
