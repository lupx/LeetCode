package com.leetcode.number219.ContainsDuplicatesNearby;

import java.util.*;

/**
 *
 * Created by Peixin Lu on 15/12/28.
 */
public class Solution {

    /**
     * hashMap来做就行了, 按照这样存: map<value, index>, index保存最近出现的value的下标
     * 遍历数组, 每个数字存入hashMap, 如果有相同的, 比较下两者之差, 如果小于k就返回true. 如果大于k, index更新成当前
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        return true;
    }

    public static void main(String[] args) {
    }
}
