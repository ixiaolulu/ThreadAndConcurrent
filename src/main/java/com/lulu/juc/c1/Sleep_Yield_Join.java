
package com.lulu.juc.c1;

/**
 *
 * sleep:调用线程sleep方法线程进入timed_waiting状态,时间到了之后状态变成就绪状态
 * join:当前线程中其他线程调用join方法，当前状态变成waiting，等join线程运行完，当前线程变成就绪状态
 * yield:当前线程让出cpu执行时间片，到就绪状态
 * @since 2020/6/29 16:20
 * @author Milo.Ding
 *
 */
public class Sleep_Yield_Join {

    public static void main(String[] args) {
        // testSleep();
        // testJoin();
        // testYield();
    }

    static void testSleep() {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        System.out.println(t.getState());
    }

    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i % 5 == 0)
                    Thread.yield();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("B" + i);
                if (i % 5 == 0)
                    Thread.yield();
            }
        }).start();
    }

    static void testJoin() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
