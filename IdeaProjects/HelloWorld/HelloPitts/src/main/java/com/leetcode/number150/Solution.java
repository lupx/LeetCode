package com.leetcode.number150;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 计算后缀表达式的结果
     * 很明显用栈!
     * 遇到数字进栈, 遇到算符, stack弹出两个数字, 计算出结果后结果入栈, 如此往复直到结束
     *
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;

        int N = tokens.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            Integer thisOne;
            String thisStr = tokens[i];
            try {
                thisOne = Integer.parseInt(thisStr);
            } catch (NumberFormatException n) {
                int a = stack.pop();
                int b = stack.pop();
                switch (thisStr) {
                    case "+": stack.push(b+a);
                        break;
                    case "-": stack.push(b-a);
                        break;
                    case "*": stack.push(b*a);
                        break;
                    case "/": stack.push(b/a);
                        break;
                }
                continue;
            }
            stack.push(thisOne);
        }
        return stack.pop();
    }

    public static void main(String[] args) {
    }

}

