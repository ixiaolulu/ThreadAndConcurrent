package com.lulu.juc.c5;

import java.util.concurrent.TimeUnit;

/**
 * @Description:* reentrantlock用于替代synchronized
 *  * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 *  * 这里是复习synchronized最原始的语义
 * @Author: Milo
 * @Date: 2020-07-07 19:24
 */
public class ReentrantLock1 {

    synchronized void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
//            if(i==2) m2();//synchronized是可重入锁
        }
    }

    synchronized void m2() {
        System.out.println("m2......");

    }

    public static void main(String[] args) {
        ReentrantLock1 t = new ReentrantLock1();
        new Thread(t::m1,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        new Thread(t::m2,"t2").start();//synchronized是互斥锁
    }
}
