package com.leetcode.number10;

/**
 * This is the DP solution to the Regular Expression Match
 * Created by PeixinLu on 15/9/16.
 */
public class DpSolution {
    /**
     * This is the O(nm) time and O(n) space DP, awesome!
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        String[] patterns = new String[p.length()];
        int i = 0, ptr = 0;
        while (i != p.length()) {//parse p into tokens[], 要么单字符,要么*二元组
            if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
                patterns[ptr++] = p.substring(i, i + 2);
                i += 2;
            }
            else {
                patterns[ptr++] = p.substring(i, i + 1);
                i += 1;
            }
        }

        boolean[] d = new boolean[s.length() + 1];
        d[0] = true;
        for (i = 1; i <= s.length(); ++i) d[i] = false; //d[]全部置为false
        for (i = 1; i <= ptr; ++i) {
            //根据tokens[], 一一判断是否和s中每个字符匹配.
            String pattern = patterns[i - 1];//获取当前token
            char c = pattern.charAt(0);//当前token第一个字符
            if (pattern.length() == 2) {//2元组情况
                for (int j = 1; j <= s.length(); ++j) {//分别针对s中字符进行匹配测试
                    d[j] = d[j] || (d[j - 1] && (c == '.' || c == s.charAt(j - 1)));
                }
            }
            else {//单个情况
                for (int j = s.length(); j >= 1; --j) {
                    d[j] = d[j - 1] && (c == '.' || c == s.charAt(j - 1));
                }
            }
            d[0] = d[0] && pattern.length() == 2;
        }
        return d[s.length()];
    }

    /**
     * 根据自己的理解写的DP, O(nm)时间, 但是空间是O(MN).时间应该是不能再优化了, 空间可优化成上面的O(slength)
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatchDP(String s, String p) {
        int slen = s.length();
        int plen = p.length();

        /**
         * 保存动态规划的中间结果,我们用dp[i][j]来表示: S{0,..i-1} 与P{0,..j-1}的匹配结果.
         */
        boolean dp[][] = new boolean[slen+1][plen+1];//上面解释了,i和j在dp里代表s和p的下标.所以,dp尺寸需要加1

        /**
         * 下面来分析一下递推公式(DP少不了这个东西!).
         * 所谓递推公式就是根据之前已经保存的状态推出当前的状态. 也即求当前dp[i][j],可根据之前的结果间接的求出
         * 假设当前求dp[i][j], 它代表了S{0->i-1}与P{0->j-1}的匹配情况. 那么有以下几个可能:
         * (1)如果p{j-1}当前不是*,情况简单,当前匹配的唯一条件就是p{j-1}要与s{i-1}匹配
         *    并且, 之前也都一直匹配, dp[i-1][j-1]匹配! 两者哪个不满足都是false,所以两个条件"&&"一下即可.
         *    得递推公式:
         *    when p{j-1}!='*', dp[i][j] = dp[i-1][j-1] && p{j-1} == s{i-1} || p{j-1} == '.'
         * (2)如果p{j-1}当前是个*, 情况比较复杂了. 首先看看有哪几种可能性, 我们设p{j-2} = X, X* 是个二元组
         *   (2.1) X没有在s中重复过, 也即X重复了0次, 所以这种情况就是只要dp[i][j-2]为true, 当前就可以为true.
         *   (2.2) X在S中...i-3,i-2,i-1的位置出现过>=1次, >=1可以拆分开理解,=1成立&&>1也成立!(这是本题最难的部分!一旦理解,这个题就是个easy题了!)
         *         那么可以假设出现一次的话, 显然必须满足 p{j-2}==s{i-1}||p{j-2}=='.'
         *         出现>1次, 还应要求, S{0->i-2}最起码要能匹配p{0->j-1}, 也即dp[i-1][j]也需为true
         *   综上, 2.1和2.2之间是或者的关系,但是2.2内部,>=1我们拆成了>1&&=1的情况,这样就是个&&的关系
         *    得递推公式:
         *    when p{j-1}=='*', dp[i][j] = dp[i][j-2] || (p{j-2}==s{i-1}||p{j-2}=='.') && dp[i-1][j]
         * 有了递推公式, 我们可以看到,当i和j分别推进到各自边界的时候,两个串的最终匹配结果一定保存在dp[slen][plen],return这个结果就可以了!
         */

        /**
         * 显然 dp[0][0] = true, 因为代表两个空串做匹配的结果,肯定是true
         */
        dp[0][0] = true;

        /**
         * 当p为空串的时候,s有字符,显然全部不可能匹配
         */
        for(int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }

        /**
         * 显然, i=0, j从1-plen遍历的各个结果,代表了p各个子串分别是否能否匹配空串s.
         * 有一定可能, 当p中j-1位置是*,并且0->j-3的匹配结果是true, 也即dp[0][j-2] = true
         * 否则,dp[0][j] =false
         * 这里, 我们把i=0的第一行计算出来
         */
        for(int j = 1; j <= p.length(); j++) {
            //之所以从1开始,是为了方便理解: j位置结果表示了p{0->j-1}的匹配结果
            //所以,显然dp[0][1]代表了p第一个字符是否能够匹配空串, 显然是不可能的
            if(j==1) dp[0][j] = false;
            else dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-2];
        }



        /**到这里,我们就已经分析完了基本边界情况以及空串情况,下来开始递推*/
        for(int i = 1; i <= slen; i++) {
            for(int j = 1; j <= plen; j++) {
                if(p.charAt(j-1) != '*') {
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1));
                }
                else {
                    dp[i][j] = dp[i][j-2]||
                            (p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)) && dp[i-1][j];
                }
            }
        }
        return dp[slen][plen];
    }

    public static void main(String[] args) {
        System.out.println(isMatchDP("aabbbbcd", "a*b*bbbcd"));
//        String a = "a";
//        System.out.println(a.substring(2));
//        boolean[][] dp = isMatchDP("aabbbbcd", "a*b*bbbcd");
//        for (int i = 0; i <= 8; i++) {
//            StringBuilder boolStr = new StringBuilder();
//            for (int j = 0; j<= 9; j++) {
//                if(dp[i][j]) {
//                    boolStr.append("true  ");
//                } else {
//                    boolStr.append("false ");
//                }
//            }
//            System.out.println(boolStr.toString());
//        }
    }
}

