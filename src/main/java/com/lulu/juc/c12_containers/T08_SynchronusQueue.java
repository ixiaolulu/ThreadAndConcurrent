package com.lulu.juc.c12_containers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 20:18
 */
public class T08_SynchronusQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();//同步queue，队列长度为0，用于两个线程传递数据

        new Thread(()->{
            while(true){
                try {
                    System.out.println(strs.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        strs.put("qqq");

        strs.put("bbb");
//        strs.add("aaa");
        System.out.println(strs.size());
    }
}
