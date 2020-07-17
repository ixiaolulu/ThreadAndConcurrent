package com.lulu.juc.c5_syncUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * @Author: Milo
 * @Date: 2020-07-07 19:34
 */
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
                if (i == 2) m2();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }

    }

    void m2() {
        try {
            lock.lock();
            System.out.println("m2....");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock2 t = new ReentrantLock2();
        new Thread(t::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        new Thread(t::m2).start();
    }
}
