package com.lulu.juc.c6_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-09 22:04
 */
public class Semaphore_4 {
    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;


    public static void main(String[] args) {
        Semaphore_4 c = new Semaphore_4();
        Semaphore s = new Semaphore(1);

        t2 = new Thread(() -> {
            System.out.println("t2 start...");

            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
            System.out.println("t2 end...");

        }, "t2");

        t1 = new Thread(() -> {
            try {
                s.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);


                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                s.acquire();
                for (int i = 5; i < 10; i++) {
                    System.out.println("add"+i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");


        t1.start();
    }

}
