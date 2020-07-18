package com.lulu.juc.c12_containers;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 18:49
 */
public class T01_ConcurrentHashMap {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();//非高并发无序 Collections.synchronizedMap
//        Map<String, String> map = new ConcurrentHashMap<>();//高并发无序 ReentrantLock+Segment+HashEntry(JDK1.7)，synchronized+CAS+HashEntry+红黑树(JDK1.8)
//        Map<String, String> map = new Hashtable<>();//高并发无序 synchronized
        Map<String, String> map = new ConcurrentSkipListMap<>();//高并发且有序CAS+synchronized
//        Map<String, String> map = new TreeMap<>();//非高并发有序 红黑树

        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "b" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(map.size());
    }
}
