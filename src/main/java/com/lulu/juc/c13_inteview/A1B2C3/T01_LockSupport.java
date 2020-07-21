/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.juc.c13_inteview.A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 *
 * @since 2020/7/21 14:18
 * @author Milo.Ding
 *
 */
public class T01_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
       char[] a = "123456789".toCharArray();
       char[] b = "ABCDEFGHI".toCharArray();
       
       t1 = new Thread(()->{
           for(char tempB : b){
               System.out.print(tempB);
               LockSupport.unpark(t2);//把t2换醒
               LockSupport.park();//t1阻塞
           }
       },"t1");

        t2 = new Thread(()->{
            for(char tempA : a){
                LockSupport.park();//t2阻塞
                System.out.print(tempA);
                LockSupport.unpark(t1);//唤醒t1
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
