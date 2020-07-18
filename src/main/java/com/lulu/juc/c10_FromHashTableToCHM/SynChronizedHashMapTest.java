package com.lulu.juc.c10_FromHashTableToCHM;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 14:20
 */
public class SynChronizedHashMapTest {
    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<UUID,UUID>());
    static int COUNT = Constants.COUNT;
    static UUID[] key = new UUID[COUNT];
    static UUID[] value = new UUID[COUNT];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;
    static final int GAP = COUNT / THREAD_COUNT;
    static UUID uuid_key_10;

    static {
        for (int i = 0; i < COUNT; i++) {
            key[i] = UUID.randomUUID();
            value[i] = UUID.randomUUID();
            if (i == 9) {
                uuid_key_10 = key[i];
            }
        }
    }

    static class MyThread extends Thread {
        int start;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + GAP; i++) {
                m.put(key[i], value[i]);
            }
        }
    }

    public static void main(String[] args) {
        //100个线程，每个线程往m里面添加10000个UUID
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new MyThread(i * GAP);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(m.size());

        //100个线程，每个线程从m里面获取key为10的元素1000000次
        startTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_0000; j++) {
                    m.get(key[10]);
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}
