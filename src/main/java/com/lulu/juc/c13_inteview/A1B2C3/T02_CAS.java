package com.lulu.juc.c13_inteview.A1B2C3;

/**
 *
 * @since 2020/7/21 14:44
 * @author Milo.Ding
 *
 */
public class T02_CAS {
    enum ReadyToRun {
        T1, T2
    }

    static volatile ReadyToRun r = ReadyToRun.T1;//使用枚举更加严谨，不可以随意改变
//    static volatile int r = 0;

    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : b) {
                while (r != ReadyToRun.T1) {
                }//占用cpu时间
                System.out.print(c);
                r = ReadyToRun.T2;

            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : a) {
                while (r != ReadyToRun.T2) {
                }
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();

    }
}
