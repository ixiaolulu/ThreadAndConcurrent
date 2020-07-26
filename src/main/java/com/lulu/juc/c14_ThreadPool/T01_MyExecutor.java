package com.lulu.juc.c14_ThreadPool;

import java.util.concurrent.Executor;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-25 11:13
 */
public class T01_MyExecutor implements Executor {

    public static void main(String[] args) {
        new T01_MyExecutor().execute(() -> System.out.println("T01_MyExecutor"));
    }


    @Override
    public void execute(Runnable command) {
//        new Thread(command).run();
        command.run();
    }
}
