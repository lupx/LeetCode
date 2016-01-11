package com.leetcode.number49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * 根据一个事实: charArray可排序, String也可排序!
     * 排序后相同的String,肯定属于一个group,
     * 最终全部按照这个排序后的串作为key, 排序前字符加入value.list中, 记录于一个map中
     * 最后输出map中所有value即可.
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Arrays.sort(strs);//String居然都可以排序!!!
        HashMap<String, List<String>> hm = new HashMap<String, List<String>>();

        for (int i = 0; i < strs.length; i++) {
            String curStr = strs[i];
            char[] charArray = curStr.toCharArray();
            Arrays.sort(charArray);
            List<String> list = hm.getOrDefault(String.valueOf(charArray), new ArrayList<String>());
            list.add(curStr);
            hm.put(String.valueOf(charArray), list);
        }

        return new ArrayList<List<String>>(hm.values());
    }
}
