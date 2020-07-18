package com.lulu.juc.c12_containers;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 19:49
 */
public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(10);

    static Random random = new Random();
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            blockingQueue.put("a"+i);
        }

//        blockingQueue.put("qqq");

//        blockingQueue.add("33434");
        System.out.println(blockingQueue.offer("13233"));
        System.out.println(blockingQueue.offer("13233",1, TimeUnit.SECONDS));
        System.out.println(blockingQueue);
    }

}
