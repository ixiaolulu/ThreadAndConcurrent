
package com.lulu.juc.c4;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * @since 2020/7/7 16:22
 * @author Milo.Ding
 *
 */
public class AtomicVsSynVsLongAdder {
    static long count1 = 0L;
    static AtomicLong count2 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        Thread[] threads = new Thread[500];

        //针对一个热点值自旋
        testAtomicLong(threads);

        //锁升级：无锁->偏向锁->轻量级锁 （自旋锁）->重量级锁
        testSynchronized(threads);

        //分段自旋：针对多个热点值自旋
        testLongAdder(threads);
    }

    public static void testLongAdder(Thread[] threads) {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads)
            t.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("testLongAdder===>LongAdder:" + count3.longValue() + " cost:" + (end - start) + "ms");
    }

    public static void testSynchronized(Thread[] threads) {

        Object lock = new Object();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    synchronized (lock) {
                        count1++;
                    }
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads)
            t.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("testSynchronized===>Synchronized:" + count1 + " cost:" + (end - start) + "ms");
    }

    public static void testAtomicLong(Thread[] threads) {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count2.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads)
            t.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("testAtomicLong===>Atomic:" + count2.get() + " cost:" + (end - start) + "ms");
    }
}
