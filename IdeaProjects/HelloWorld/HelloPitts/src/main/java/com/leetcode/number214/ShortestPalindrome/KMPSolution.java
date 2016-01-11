package com.leetcode.number214.ShortestPalindrome;

/**
 * 思想是用KMP算法,找最长回文串
 * Created by PeixinLu on 15/12/27.
 */
public class KMPSolution {

    /**
     * 计算一个字符串的next[]数组
     * @param s
     * @return 
     */
    public int[] getNext(String s) {
        int len = s.length();
        int j = 0;

        int[] next = new int[len + 1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
        next[0] = next[1] = 0; //next[0]肯定是0, next[1]对应了单个字符的前后缀最长公共部分, 肯定也是0

        for (int i = 1; i < len; i++) {//i表示字符串的下标，从0开始
            //j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
            /**
             * 这一步是kmp算法的核心,也是最难懂的地方!
             * 在i,j本来匹配的过程中, 突然出现了一个字符, 使得两边失配了, 那显然, j的增长就要停止了!
             * 那么,在遇到这种情况的时候, 要考虑无法增长是肯定的, 但是我们既然是要找最大公共前后缀长度, 所以要适当的缩小j,只要当前这个字符又能匹配不就可以了么!
             * 怎么缩小呢?
             * 这里一定要记住这个事实, next函数保存的是前后缀最大公共长度. 那么next[j]保存的就是字符串从0到j-1位置能够和后面某个后缀匹配的长度
             * 那么, 把j移动到这个位置上, 只要i位置和新的j位置的字符匹配, 那么最小化缩小公共前后缀的目的就实现了.
             * 而如果还是不一样, j继续往前缩小.  直到遇到能够和当前i匹配的字符 (最差情况, j移动到0位置还没有和当前i匹配, 那么根据后面代码, next[i + 1] = 0)
             *
             */
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j];
            }
            if(s.charAt(i) == s.charAt(j)) j++;//比较新纳入的i和j是否相同, 如果相同表示再次匹配成功, j前进一格
            next[i + 1] = j;//i+1的意义在于, 因为next数组下标比字符串数组下标小了1, 所以这里加1. 代表了s中前i+1个字符的最大公共前后缀长度为j
        }
        return next;
    }
}
