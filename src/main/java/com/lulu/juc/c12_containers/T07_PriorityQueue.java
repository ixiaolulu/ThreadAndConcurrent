package com.lulu.juc.c12_containers;

import java.util.PriorityQueue;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 20:17
 */
public class T07_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }

    }
}
