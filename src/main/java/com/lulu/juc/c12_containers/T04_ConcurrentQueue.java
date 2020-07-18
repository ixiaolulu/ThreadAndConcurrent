package com.lulu.juc.c12_containers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 19:36
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for(int i=0; i<10; i++) {
            strs.offer("a" + i);  //add
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());
        System.out.println(strs.size());

        System.out.println(strs.peek());
        System.out.println(strs.size());

    }
}
