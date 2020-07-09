package com.lulu.juc.c6_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-09 21:41
 */
public class CountDownLatch_2 {

    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        CountDownLatch_2 c = new CountDownLatch_2();

        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start...");
            try {
                //t2等待执行
                c2.await();
                //让t1执行
                c1.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end...");

        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 start...");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add-" + i);
                if (c.size() == 5) {
                    c2.countDown();
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1 end...");
        }, "t1").start();

    }
}
