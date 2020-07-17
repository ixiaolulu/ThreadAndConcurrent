package com.lulu.juc.c8_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 *
 * @since 2020/7/17 15:46
 * @author Milo.Ding
 *
 */
public class ThreadLocalTest_1 {

     volatile static Person p = new Person();


    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "zhangsan";
        }).start();
    }

}

class Person{
    String name = "lisi";
}