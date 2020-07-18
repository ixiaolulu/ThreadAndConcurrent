
package com.lulu.juc.c9_containers;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @since 2020/7/17 16:36
 * @author Milo.Ding
 *
 */
public class HelloQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.add(1);
        queue.add(2);
        queue.peek();
        queue.poll();
        try {
            queue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.add(3);
        System.out.println(queue);
    }
}
