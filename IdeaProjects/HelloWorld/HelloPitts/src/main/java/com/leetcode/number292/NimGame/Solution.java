package com.leetcode.number292.NimGame;


/**
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table,
 *  each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner.
 *  You will take the first turn to remove the stones.
 * Both of you are very clever and have optimal strategies for the game.
 *
 * Write a function to determine whether you can win the game given the number of stones in the heap.
 * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove,
 * the last stone will always be removed by your friend.
 *
 * Created by Peixin Lu on 16/1/10.
 */
public class Solution {

    /**
     * 实际分析规律就能算出来
     * 1,2,3个石头的时候必赢, 4个石头的时候必输.
     * 那么,
     * 5个石头的时候呢? 可以这么理解, 5去掉i个石头, 然后变成由对方先走的局面, 那么5-i只要等于对方先走必输的局面, 我就必赢. 所以, 5个石头的时候,我取1个必赢
     * 6个石头同理, 6-2 =4, 这个时候相当于总共4个石头 对方先走的局面. 那么我必赢
     * 7个石头同理, 取3个必赢.
     * 8个石头就必输了, 因为我无论取走几个(1,2,3)都变成了对方必赢的局面(5,6,7)
     *
     * 所以, 得出一个结论, 4的整数倍的时候, 我必输.其他时候,我必赢!
     * @param n
     * @return
     */
    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    public static void main(String[] args) {
        System.out.println(canWinNim(3));
    }
}
