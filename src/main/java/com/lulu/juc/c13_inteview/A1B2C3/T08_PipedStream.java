/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.juc.c13_inteview.A1B2C3;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 *
 * @since 2020/7/21 18:43
 * @author Milo.Ding
 *
 */
public class T08_PipedStream {

    public static void main(String[] args) throws Exception {
        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your Turn";

        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char c : b) {
                    System.out.print(c);
                    output1.write(msg.getBytes());
                    input1.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        continue;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "t1").start();

        new Thread(() -> {

            byte[] buffer = new byte[9];

            try {
                for (char c : a) {
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.print(c);
                    }
                    output2.write(msg.getBytes());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }


}
