package com.lulu.juc.c5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-07 21:23
 */
public class ReadWriteLockTest {

    static Lock lock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();


    private static int value;


    public static void main(String[] args) {

//        Runnable readR = ()-> read(lock);
        Runnable readR = ()-> read(readLock);
//        Runnable writeR = ()-> write(lock,2);
        Runnable writeR = ()-> write(writeLock,2);

        for (int i = 0; i < 10; i++) new Thread(readR).start();
        for (int i = 0; i < 2; i++) new Thread(writeR).start();

    }

    static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
