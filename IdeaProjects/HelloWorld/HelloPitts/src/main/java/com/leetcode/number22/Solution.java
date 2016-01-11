package com.leetcode.number22;

import com.leetcode.number21.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 *
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {
    /**
     * 主驱动程序
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        if(n == 0) {
            list.add(new String(""));
            return list;
        }
        Map<String, Integer> map = new HashMap<String, Integer>(); //用于记录已经出现过的串
        return recursiveGenerator("", map, n);
    }

    /**
     * 递归主方法
     * 在已有串: para中找出各个位置, 分别插入(),并形成list返回
     * @param para 已有串
     * @param need 还需几个(), need==1时为基准条件
     * @return
     */
    public static List<String> recursiveGenerator(String para, Map<String, Integer> map, int need) {
        if (need == 0) {
            //base condition
            return new ArrayList<String>();
        } else {
            //non base condition
            //在para中找各个位置,加入一个(),然后直接返回
            List<String> list = new ArrayList<String>();
            for (int i = 0; i <= para.length(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(para.substring(0,i))
                        .append("()").append(para.substring(i, para.length()));
                //子结果去重
                if(map.containsKey(sb.toString())) {continue;}
                else {
                    map.put(sb.toString(), 1);
                }
                List<String> rec = recursiveGenerator(sb.toString(), map, need - 1);
                if(rec.size() == 0) {
                    list.add(sb.toString());
                } else {
                    for(String s : rec) {
                        list.add(s);
                    }
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
//        System.out.println("()".substring(0,1));
//        System.out.println("()".substring(2,2));

        List<String> s = generateParenthesis(3);
        System.out.println(s.size());

        for(String str : s) {
            System.out.println(str);
        }
    }

}
