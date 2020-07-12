package com.lulu.juc.c8_RefTypeAndThreadLocal;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-12 19:35
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
