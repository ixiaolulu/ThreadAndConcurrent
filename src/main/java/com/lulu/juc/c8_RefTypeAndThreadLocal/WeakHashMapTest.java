package com.lulu.juc.c8_RefTypeAndThreadLocal;

import java.util.WeakHashMap;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 12:04
 */
public class WeakHashMapTest {
    public static void main(String[] args) {

        //People没有强引用时候，调用GC，会回收key指向的对(People)，然后把Entry的引用放到ReferenceQueue队列中，
        //遍历引用队列，然后删除已被回收的键值对(从数组移除，
        //改变单链表结点引用，将value赋值为null)，
        //该方法会在WeakHashMap增删改查、扩容的地方调用
        WeakHashMap<People, Object> peopleWeakHashMap = new WeakHashMap<>();
        People p = new People("xiaoming", "小明");
        peopleWeakHashMap.put(p, new Object());

        p = null;
        System.gc();
        System.out.println(peopleWeakHashMap.toString());


        //Integer保留了-128到127的缓存。不会被回收
//        WeakHashMap<Integer,Object> map = new WeakHashMap<>();
//        for (int i = 0; i < 1000; i++) {
//            map.put(i,new Object());
//            System.gc();
//            System.out.println(map.size());
//        }
    }
}


class People {
    private String id;

    private String name;

    public People(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

