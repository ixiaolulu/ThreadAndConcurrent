
package com.lulu.juc.c1;

/**
 * 创建线程的三种方式 1，Thread 2,Runnable 3,Excutors.newCachedThread
 * 
 * @since 2020/6/29 15:43
 * @author Milo.Ding
 *
 */
public class HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("myThread");
        }
    }

    static class MyRun implements Runnable {

        @Override
        public void run() {
            System.out.println("myRun");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        new Thread(new MyRun()).start();

        new Thread(() -> {
            System.out.println("hello lambda!");
        }).start();

    }
}
