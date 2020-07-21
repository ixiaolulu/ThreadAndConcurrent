
package com.lulu.juc.c13_inteview.A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @since 2020/7/21 14:54
 * @author Milo.Ding
 *
 */
public class T03_AtomicInteger {

    static AtomicInteger threadNo = new AtomicInteger(1);


    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : b) {
                while (threadNo.get() != 1) {
                }//占用cpu时间
                System.out.print(c);
                threadNo.set(2);

            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : a) {
                while (threadNo.get() != 2) {
                }
                System.out.print(c);
                threadNo.set(1);
            }
        }, "t2").start();
    }


}
