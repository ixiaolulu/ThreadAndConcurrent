package com.lulu.juc.c4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 解决同样的问题的更高效的方法，使用AtomXXX类
 *  * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 * @Author: Milo
 * @Date: 2020-07-05 11:14
 */
public class AtomicIntegerTest {
//    int count = 0;
     AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
//            count++;
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        AtomicIntegerTest t = new AtomicIntegerTest();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(t::m, "thread-" + i));
        }

        threadList.stream().forEach(o -> o.start());

        threadList.stream().forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }

}
