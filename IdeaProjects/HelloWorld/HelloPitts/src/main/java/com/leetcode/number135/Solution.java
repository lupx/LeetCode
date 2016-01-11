package com.leetcode.number135;

import java.util.Arrays;
import java.util.Stack;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * (1) Each child must have at least one candy.
 * (2) Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 还是用贪心思想,同时用到了递减栈的概念
     * 用stack来遍历保存ratings的index.
     * 出入栈规则是:
     * (1)若栈为空, 当前index直接入栈
     * (2)若栈顶元素值>当前index位置值, 当前位置入栈
     * (3)若当前index位置值>栈顶值, 栈不断pop,直到栈顶元素>当前index位置值
     *    上面, pop出的index, 在结果集左右最大值上+1, 计入结果集index位置
     * 最后, 栈里所有元素全部pop, 都和结果集上左右比, 在左右最大值上+1即为pop出index位置的值
     *
     * 最后, 把结果集加起来即可
     *
     * 速度巨慢!
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        if (ratings.length == 1) return 1;
        Stack<Integer> stack = new Stack<>();//保存递减index
        int[] rst = new int[ratings.length];//结果集
        Arrays.fill(rst, Integer.MIN_VALUE);

        //遍历ratings
        for (int i = 0; i < ratings.length; i++) {
            if (i == 0) stack.push(i);
            else {
                if (ratings[i] <= ratings[stack.peek()]) {
                    stack.push(i);
                } else {
                    //找到了最小的ratings
                    while (!stack.isEmpty()
                            && ratings[stack.peek()] < ratings[i]) {
                        int index = stack.pop();
                        if (index == 0) {
                            if (ratings[index] == ratings[index + 1]) {
                                rst[index] = 1;
                            } else {
                                rst[index] = rst[index + 1] == Integer.MIN_VALUE ?
                                        1 : rst[index + 1] + 1;
                            }
                        } else {
                            if (ratings[index] == ratings[index + 1]
                                    && ratings[index] <= ratings[index - 1]) {
                                rst[index] = 1;
                            } else if (ratings[index] == ratings[index + 1]
                                    && ratings[index] > ratings[index - 1]) {
                                rst[index] = rst[index - 1] + 1;
                            } else {
                                if (rst[index - 1] == Integer.MIN_VALUE
                                        && rst[index + 1] == Integer.MIN_VALUE) {
                                    rst[index] = 1;
                                } else {
                                    rst[index] = Math.max(rst[index - 1], rst[index + 1]) + 1;
                                }
                            }
                        }
                    }
                    stack.push(i);
                }
            }
        }

        while (!stack.empty()) {
            int index = stack.pop();
            if (index == ratings.length - 1) {
                rst[index] = rst[index - 1] == Integer.MIN_VALUE ?
                        1 : rst[index - 1] + 1;
            } else if (index == 0) {
                if (ratings[index] == ratings[index + 1]) {
                    rst[index] = 1;
                } else {
                    rst[index] = rst[index + 1] == Integer.MIN_VALUE ?
                            1 : rst[index + 1] + 1;
                }
            } else {
                if (ratings[index] == ratings[index + 1]
                        && ratings[index] <= ratings[index - 1]) {
                    rst[index] = 1;
                } else if (ratings[index] == ratings[index + 1]
                        && ratings[index] > ratings[index - 1]) {
                    rst[index] = rst[index - 1] + 1;
                } else {
                    if (rst[index - 1] == Integer.MIN_VALUE
                            && rst[index + 1] == Integer.MIN_VALUE) {
                        rst[index] = 1;
                    } else {
                        rst[index] = Math.max(rst[index - 1], rst[index + 1]) + 1;
                    }
                }
            }
        }
        int sum = 0;
        for (int i : rst) {
            sum += i;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(candy(new int[]{
                58,21,72,77,48,9,38,71,68, 77,
                82,47,25,94,89,54,26,54,54,99,
                64,71, 76,63,81,82,60,64,29,51,
                87,87,72,12,16,20,21,54,43,41,
                83,77,41,61, 72,82,15,50,36,69,
                49,53,92,77,16,73,12,28,37,41,
                79,25,80,3,37,48, 23,10,55,19,
                51,38,96,92,99, 68,75,14,18,63,
                35,19,68,28,49,36,53,61, 64,91,
                2,43,68,34,46,57,82,22,67,89
        }));
//        System.out.println(candy(new int[]{
//                1,5,5,3,2,1,8
//        }));
    }
}

