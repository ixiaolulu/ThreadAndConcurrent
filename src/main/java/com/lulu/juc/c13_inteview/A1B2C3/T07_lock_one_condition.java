
package com.lulu.juc.c13_inteview.A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @since 2020/7/21 15:57
 * @author Milo.Ding
 *
 */
public class T07_lock_one_condition {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : b) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : a) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
