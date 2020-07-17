/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.juc.c8_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 *
 * @since 2020/7/17 16:18
 * @author Milo.Ding
 *
 */
public class ThreadLocalTest_2 {

   static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Person p = new Person();
            p.name = "123";
            tl.set(p);
            System.out.println(tl.get().name);
            tl.remove();
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           tl.set(new Person());
            tl.remove();
        }).start();
    }

}
