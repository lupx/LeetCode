package com.leetcode.number204;

/**
 * Created by PeixinLu on 15/12/23.
 */
public class Test {
    public static void main(String[] args) {
        int n = 0;
        for (int i = 2; i < 200; i++) {
            if (i % 2 != 0 && i % 3 != 0 && i % 5 !=0 && i%7 !=0) {
                System.out.println(i);
                n++;
            }
        }
        System.out.println(n + 4);
    }
}
