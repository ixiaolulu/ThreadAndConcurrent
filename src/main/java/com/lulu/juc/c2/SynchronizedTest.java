
package com.lulu.juc.c2;

/**
 *
 * @since 2020/6/29 17:41
 * @author Milo.Ding
 *
 */
public class SynchronizedTest {
    private static int count = 100;
    private static final Object o = new Object();

    public static void m() {
         synchronized (o) {
         count--;
         System.out.println(Thread.currentThread().getName() + " count = " + count);
         }

        synchronized (SynchronizedTest.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized void mm() {// 等同于synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public void mmm() {
        synchronized (SynchronizedTest.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized static void n() {// 等同于synchronized(SynchronizedTest.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                test.n();
            }).start();
        }
    }
}
