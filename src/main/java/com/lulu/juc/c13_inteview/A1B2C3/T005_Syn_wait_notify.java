
package com.lulu.juc.c13_inteview.A1B2C3;

/**
 *
 * @since 2020/7/21 15:33
 * @author Milo.Ding
 *
 */
public class T005_Syn_wait_notify {

    public static void main(String[] args) {
        final Object o = new Object();

        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : b) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();// 会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须，否则无法停止程序
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : a) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }

}
