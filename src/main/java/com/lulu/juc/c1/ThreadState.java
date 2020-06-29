
package com.lulu.juc.c1;

/**
 *
 * @since 2020/6/29 16:59
 * @author Milo.Ding
 *
 */
public class ThreadState {
    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    static class BlockThread extends Thread {
        String name;
        Object lock;

        public BlockThread(String name, Object lock) {
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("Thread:" + name + " in run.");
            synchronized (lock) {
                System.out.println("Thread:" + name + " hold the lock.");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread:" + name + " release the lock.");

            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        BlockThread t1 = new BlockThread("t1",lock);
        BlockThread t2 = new BlockThread("t2",lock);

        t1.start();
        t2.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread t1's state " + t1.getState());
        System.out.println("Thread t2's state " + t2.getState());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread t1's state " + t1.getState());
        System.out.println("Thread t2's state " + t2.getState());
//        Thread t = new MyThread();
//        System.out.println(t.getState());
//        t.start();
//        System.out.println(t.getState());
//
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(t.getState());
    }
}
