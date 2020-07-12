package com.lulu.juc.c7_AQS;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-12 19:17
 */
public class VarHandleTest {
    private int x = 8;
    private static VarHandle X;

    static {
        try {
            X = MethodHandles.lookup().findVarHandle(VarHandleTest.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VarHandleTest test = new VarHandleTest();
        //直接写和写
        System.out.println(X.get(test));
        X.set(test, 9);
        System.out.println(test.x);

        //原子性操作
        X.compareAndSet(test, 9, 10);
        System.out.println(test.x);

        //原子性操作
        X.getAndAdd(test, 16);
        System.out.println(test.x);
    }
}
