package com.lulu.juc.c12_containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 19:33
 */
public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> s = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(s);
    }
}
