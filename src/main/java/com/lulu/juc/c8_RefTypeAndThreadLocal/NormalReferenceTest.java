package com.lulu.juc.c8_RefTypeAndThreadLocal;

import java.io.IOException;

/**
 * @Description: 强引用
 * @Author: Milo
 * @Date: 2020-07-12 19:38
 */
public class NormalReferenceTest {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;

        System.gc();

        System.in.read();
    }
}
