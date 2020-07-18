package com.lulu.juc.c9_containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 13:16
 */
public class CollectionListSetQueue {

    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList();
        c.add(1);
        c.add(2);
        c.add(4);
        c.add(3);
        c.add(3);
        c.add(null);
        c.stream().forEach(System.out::println);
        System.out.println("-----------------------");


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(3);
        list.add(null);
        list.stream().forEach(System.out::println);

        System.out.println("-----------------------");

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(1);
        set.add(null);
        set.stream().forEach(System.out::println);
        System.out.println("-----------------------");


        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(3);
        queue.add(null);
        queue.poll();
        queue.peek();
        queue.stream().forEach(System.out::println);



    }

}
