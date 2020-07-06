package com.lulu.juc.c3;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-03 22:07
 */
public class VolatileReference2 {
    private static class Data {
         int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    volatile static Data data;

    public static void main(String[] args) {
        Thread writer = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                data = new Data(i, i);
            }
            System.out.println("writer...");
        });

        Thread reader = new Thread(()->{
            while (data == null) {

            }
            int x = data.a;
            int y = data.b;
            System.out.println(x+"-"+y);
            if(x != y) {
                System.out.printf("a = %s, b=%s%n", x, y);
            }
        });

        reader.start();
        writer.start();

        try {
            reader.join();
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
