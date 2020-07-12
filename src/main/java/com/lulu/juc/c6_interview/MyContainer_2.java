package com.lulu.juc.c6_interview;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:* 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 * @Author: Milo
 * @Date: 2020-07-10 20:38
 */
public class MyContainer_2<T> {

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    public void put(T t) {

        try {
            lock.lock();
            while (lists.size() == MAX) {
                producer.await();
            }

            lists.add(t);
            ++count;
            consumer.signalAll();//通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        MyContainer_1<String> container_1 = new MyContainer_1<>();
        //启动消费线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++)
                    System.out.println(Thread.currentThread().getName() + "-consume:" + container_1.get());
            }, "c" + i).start();

        }

        //启动生产线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) container_1.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();

        }
    }

}
