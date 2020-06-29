
package com.lulu.juc.c2;

/**
 * volatile:保证线可见性和禁止指令重排序 synchronized:保证原子性和可见性
 * 
 * @since 2020/6/29 17:52
 * @author Milo.Ding
 *
 */
public class SynchronizedVolatile implements Runnable {

    private /*volatile*/ int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        SynchronizedVolatile t = new SynchronizedVolatile();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }

}
