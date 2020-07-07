package com.lulu.juc.c4;

import sun.misc.Unsafe;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-07 19:11
 */
public class UnsafeTest {

    static class M{
        private M(){

        }
        int i = 0;
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M) unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}
