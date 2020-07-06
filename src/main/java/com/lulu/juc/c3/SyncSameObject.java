package com.lulu.juc.c3;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-05 11:04
 */
public class SyncSameObject {

    /*final*/ Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        SyncSameObject t = new SyncSameObject();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //锁对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会

        Thread t2 = new Thread(t::m, "t2");
        t.o = new Object();

        t2.start();


    }
}
