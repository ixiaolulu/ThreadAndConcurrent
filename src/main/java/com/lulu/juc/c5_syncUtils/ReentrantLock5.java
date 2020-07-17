package com.lulu.juc.c5_syncUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: ReentrantLock还可以指定为公平锁
 * @Author: Milo
 * @Date: 2020-07-07 20:22
 */
public class ReentrantLock5 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示为公平锁，请对比输出结果


    public static void main(String[] args) {
        ReentrantLock5 rl = new ReentrantLock5();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "-获得锁" + i);
            } finally {
                lock.unlock();
            }
        }
    }
}
