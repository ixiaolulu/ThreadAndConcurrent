
package com.lulu.juc.c13_inteview.A1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @since 2020/7/21 15:20
 * @author Milo.Ding
 *
 */
public class T04_BlockingQueue {
    static BlockingQueue<String> q1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue(1);


    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : b) {
                System.out.print(c);
                try {
                    q1.put("ok");
                    q2.take();//阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : a) {
                try {
                    q1.take();//阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);

                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }

}
