package com.lulu.juc.c7_AQS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-11 12:34
 */
public class TestReentrantLock {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();

        lock.unlock();
    }
}
