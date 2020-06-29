
package com.lulu.juc.c2;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁. 也就是说synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 * 
 * @since 2020/6/29 18:50
 * @author Milo.Ding
 *
 */
public class SynchronizedReentrant {

    public synchronized void m1() {
        System.out.println("m1...");
    }

    public synchronized void m2() {
        System.out.println("m2...");
        m1();
    }

    public static void main(String[] args) {
        new SynchronizedReentrant().m2();
        new SynchronizedReentrantChild().m1();
    }

}

class SynchronizedReentrantChild extends SynchronizedReentrant {
    @Override
    public synchronized void m1() {
        System.out.println("child m1 start...");
        super.m1();
        System.out.println("child m1 end...");
    }
}
