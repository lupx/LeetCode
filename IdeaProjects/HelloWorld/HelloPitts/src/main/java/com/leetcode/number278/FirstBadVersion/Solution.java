package com.leetcode.number278.FirstBadVersion;

/**
 * First Bad Version
 * find the first bad version follow which will all be bad
 * like to find the first 1 in {0,0,0,0,0,0,1,1,1,1}
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * 继续二分!
     * 相当于用二分搜索找第一个坏版本.
     * @param n
     * @return
     */
//    public int firstBadVersion(int n) {
//        int start = 0;
//        int end = n;
//        while (start + 1 < end) {
//            int mid = start + (end - start) / 2;
//            if (isBadVersion(mid)) {
//                end = mid;
//            } else {
//                start = mid;
//            }
//        }
//        if (isBadVersion(start)) {
//            return start;
//        }
//        if (isBadVersion(end)){
//            return end;
//        }
//        return -1;//应该走不到这里.
//    }



    public static void main(String[] args) {
    }
}
