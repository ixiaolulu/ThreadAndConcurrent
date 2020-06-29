
package com.lulu.juc.c2;

/**
 * 同步和非同步方法可以同时调用
 * 
 * @since 2020/6/29 18:44
 * @author Milo.Ding
 *
 */
public class ThreadLambda {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "start......");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "end......");
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + "start......");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "end......");
    }

    public static void main(String[] args) {
        ThreadLambda tl = new ThreadLambda();
        new Thread(tl::m1, "t1").start();
        new Thread(tl::m2, "t2").start();

        // 1.8之前的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                tl.m1();
            }
        }).start();
    }
}
