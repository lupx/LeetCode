package com.leetcode.number32;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 *
 * Created by PeixinLu on 15/10/4.
 */
public class Solution {

    /**
     *
     * 1. 首先构造动态数组 d[s.length()]
     * 2. 用Stack来作栈,用来匹配 '(' 和 ')'
     * 3. 从0位->最后一位遍历, 遇到 ( 入栈, 遇到 ) 出栈.
     *   3.1 每次入栈时,其他不用管,直接d[i] = 0
     *   3.2 每次出栈时,如果栈已经为空,当前d[i]记为0;
     *   3.3 每次出栈后,d[i] = 2; 说明前面肯定有一个(和它匹配.
     * 4. 最后从后往前遍历.数最多的2的个数.
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }
        int n = s.length();
        int[] d = new int[n];

        Stack<Character> stack = new Stack<Character>();

        if (s.charAt(0) == '(') {
            stack.push(s.charAt(0));
            d[0] = 0;
        } else {
            d[0] = 0;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
                //最后一位是'('的特殊情况
                d[i] = 0;
            } else {
                if (stack.size() == 0 ) {
                    //说明当前不匹配
                    //当前置为0
                    d[i] = 0;
                } else {
                    stack.pop();
                    d[i] = 2;
                }
            }
        }

        /**
         * 整理d[], 现在d[]里只有0和2
         * 从后往前, 对2计数, 遇到2就加1, 遇到0就-1;再维护一个总2的个数.遇到2就加1
         * 当计数<0的时候,2计数清0, 比较max和当前2总数. 需要时更新max
         * 当循环彻底结束后, return 2的总个数 * 2 即为所求
         */
        int twoCount = 0;
        int totalTwo = 0;
        int max = 0;
        int i = d.length - 1;
        while ( i >= 0) {
            if (d[i] == 0) {
                twoCount--;
                if (twoCount < 0 || i == 0) {
                    twoCount = 0;
                    if (totalTwo > max) {
                        max = totalTwo;
                    }
                    totalTwo = 0;
                }
                i--;
            } else {
                twoCount++;
                totalTwo++;
                i--;
            }
        }
        return max * 2;
    }


    public static int longestValidParentheses2(String s) {
        Stack<Integer> st = new Stack<Integer>();
//        st.push(-1);
        int len = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == ')' && !st.empty() && s.charAt(st.peek()) == '('){
                Integer a = st.pop();
                len = Math.max(len, i - a + 1);
                continue;
            }
            if (s.charAt(i) == '(') {
                st.push(i);
            }
        }
        return len;
    }


    /**
     * DP解法
     * d[i] 代表了0-i长度内最长合法串的长度, 从而有递推公式:
     *
     * (1) d[i] == '('的时候,d[i]直接写0
     * (2) d[i] == ')'的时候,有几个可能性:
     *     (2.1) d[i-1] == '(', 那么就是"()"的情况,显然d[i] = d[i-2] + 2;
     *     (2.2) d[i-1] == ')',那么就是"))"的情况,这个时候比较复杂:
     *       (2.2.1) i往前找,有一个合法对称位,那么d[i]就好求
     *       (2.2.2) i往前找没找到对称位,说明i是一个不合法的),所以d[i]=0;
     *
     *  那么怎么判断i有对称位?
     *  首先,不管)之前有多少个), 其中的第一个)肯定是有一个值2(因为它的左边就是'('),
     *  往后第二个)就是4(因为多了左边的'('和右边的')'),再往后就是6,8...
     *  那么d[i-1]保存的值就是包括了前面从i对称位下一位开始到i-1位的所有(..()..)的长度.
     *  所以,当前i位')'的对称位,如果有的话,一定等于 i - d[i-1] - 1.
     *  那么,合法对称位就是 d[i-d[i-1]-1] == '(',那就合法. 如果不合法,那么当前d[i]=0就continue了;
     *  那么,有合法对称位的时候,就有递推公式:
     *      d[i] = d[i-1] + 2 + d[i-d[i-1]-2].
     *  d[i-1] + 2好理解,就是在前一个)的基础上再加2,相当于把对称位和当前位算上去了.
     *  为什么要再加一个d[i - d[i-1] - 2]呢?
     *  先来看看d[i - d[i - 1] - 2]到底是啥:
     *  上面说了,d[i - d[i - 1]]就是当前位的对称位的值,也就是从0 - 对称位的最长合法串的长度.
     *  而这里隐藏了一个可能性,就是这个对称位再往前走一位,就是上一个合法串的最后一位! i - d[i-1] - 2
     *  如果这个位置存的不是0, 那么说明i的对称位之前是一个合法串! 从对称位到i也是一个合法串!
     *  那这两个串明显可以衔接的嘛!
     *  这就是为什么还要加 d[i - d[i - 1] - 2]的根本原因!把它加进来不就衔接上了么!
     *  换个可能性来看,如果d[i - d[i - 1] - 2]==0, 那加进来也无所谓了.不影响结果!
     *  程序如下!
     * @param s 给定的字符串
     * @return 最长合法串的长度
     */
    public static int longestValidParenthesesDP(String s) {
        int n = s.length();
        int[] d = new int[n + 1];
        for (int i = 0; i < d.length; i++) {
            d[i] = 0;
        }
        int max = 0;
        for (int i = 2; i < d.length; i++) {
            int k = i - 1;
            if(s.charAt(k) == ')') {
                if (s.charAt(k - 1) == '(') {
                    //()()的情况
                    if (k == 1) { //防越界
                        d[i] = 2;
                    } else {
                        d[i] = d[i - 2] + 2;
                    }
                } else {
                    //(...()..))的情况
                    //先找合法对称位
                    //因为i为s中实际位置+1,
                    if (i - d[i - 1] - 2 >= 0 && s.charAt(i - d[i - 1] - 2) == '(') {
                        //找到合法对称位
                        d[i] = d[i - 1] + 2 + d[i - d[i - 1] - 2];
                    } else {
                        d[i] = 0;
                    }
                }
                max = Math.max(max, d[i]);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        String s = ")()())()())";
        System.out.println(longestValidParenthesesDP(s));

    }
}
