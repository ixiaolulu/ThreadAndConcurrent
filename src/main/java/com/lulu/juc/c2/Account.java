package com.lulu.juc.c2;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-03 21:17
 */
public class Account {
    String name;

    double balance =50;

    public synchronized void set(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }


    public double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(()->account.set("zhangsan",100)).start();
        new Thread(()->account.set("zhangsan",30)).start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(account.getBalance("zhangsan"));


//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(account.getBalance("zhangsan"));

    }
}
