package com.lulu.juc.c3;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: synchronized可以保证可见性和原子性，volatile只能保证可见性
 * @Author: Milo
 * @Date: 2020-07-05 10:43
 */
public class VolatileVstSync {

    volatile int count = 0;

    //volatile只能保证可见性
    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    //synchronized可以保证可见性和原子性
    synchronized void m1() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

   // synchronized优化
   // 同步代码块中的语句越少越好


    void m2() {
       for (int i = 0; i < 10000; i++) {
           synchronized (this){
               count++;
           }
       }
   }

    public static void main(String[] args) {
        VolatileVstSync v = new VolatileVstSync();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            threadList.add(new Thread(v::m2, "thread-" + i));
        }

        threadList.forEach(t->t.start());
        threadList.forEach(t->
        {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(v.count);
    }

}
