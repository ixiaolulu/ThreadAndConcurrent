package com.lulu.juc.c8_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-12 20:48
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal();
        tl.set(new M());
        System.out.println(tl.get());
        tl.remove();
    }
}
