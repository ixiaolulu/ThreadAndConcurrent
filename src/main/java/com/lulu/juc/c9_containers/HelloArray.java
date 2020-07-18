package com.lulu.juc.c9_containers;

import java.util.Arrays;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-07-18 13:11
 */
public class HelloArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,2,19,8,5,6,7};
        System.out.println(Arrays.toString(arr));
        Arrays.stream(arr).map(i->i+1).forEach(i-> System.out.println(i));
    }
}
