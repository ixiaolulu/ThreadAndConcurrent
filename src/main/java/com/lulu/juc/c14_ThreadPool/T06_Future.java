package com.lulu.juc.c14_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-26 10:44
 */
public class T06_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            return 1000;
        });

        new Thread(futureTask).start();

        System.out.println(futureTask.get());
        System.out.println(futureTask.isCancelled());
        System.out.println(futureTask.isDone());

    }
}
