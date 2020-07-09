package com.lulu.juc.c6_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-09 21:56
 */
public class LockSupport_3 {
    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;


    public static void main(String[] args) {
        LockSupport_3 c = new LockSupport_3();

        t2 = new Thread(() -> {
            System.out.println("t2 start...");

            LockSupport.park();
            LockSupport.unpark(t1);

            System.out.println("t2 end...");

        }, "t2");

        t1 = new Thread(() -> {
            System.out.println("t1 start...");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add-" + i);
                if (c.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
            System.out.println("t1 end...");
        }, "t1");


        t1.start();
        t2.start();
    }

}
