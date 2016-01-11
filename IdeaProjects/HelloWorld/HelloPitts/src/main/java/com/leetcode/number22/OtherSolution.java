package com.leetcode.number22;

import java.util.ArrayList;
import java.util.List;

/**
 * 另一种递归解法,速度更快,学习一下
 * Created by PeixinLu on 15/9/29.
 */
public class OtherSolution {
    /**
     * 定义全局变量,无需再传值到各个方法
     */
    static List<String> list = new ArrayList<String>();

    public static List<String> generateParenthesis(int n) {
        /**
         * 初始状态,左括号和右括号都可以添加n个,所以左右都传了n
         */
        generateLeftsAndRights("",n,n);
        return list;
    }

    /**
     * DFS方法
     * 该方法的本质是,每次调用都深度优先用掉左括号,然后再用掉右括号.
     * @param subList 记录每次递归时候当前串, 方法将在subList上继续添加括号
     * @param left 左括号还剩下的个数
     * @param right 右括号还剩下的个数
     */
    private static void generateLeftsAndRights(String subList,int left, int right){
        /**
         * 最后可能出现的情况, 右括号比左括号先用完, 明显不是合法的串,应剔除
         */
        if(left > right) return;

        /**
         * 左括号还剩得有, 优先用左括号
         * 该方法将不断深搜, 直到左括号全部用完
         */
        if(left > 0){
            generateLeftsAndRights(subList + "(", left-1, right);
        }

        /**
         * 开始用右括号
         */
        if(right > 0){
            generateLeftsAndRights(subList + ")", left, right-1);
        }

        /**
         * 基准情况, 各自都用完, 说明已经形成了一个合法的串,将该串加入list返回
         */
        if(left == 0 && right == 0){
            list.add(subList);
            return;
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
