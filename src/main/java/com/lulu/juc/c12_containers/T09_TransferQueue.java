package com.lulu.juc.c12_containers;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @Description: transfer放上去一个数据，等待线程消费，消费完成在去干其他事情
 * @Author: Milo
 * @Date: 2020-07-18 20:23
 */
public class T09_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

//        strs.put("aaa");


        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
