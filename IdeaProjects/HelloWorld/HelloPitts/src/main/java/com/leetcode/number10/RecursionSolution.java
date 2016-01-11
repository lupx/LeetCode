package com.leetcode.number10;

/**
 * Created by PeixinLu on 15/9/17.
 */
public class RecursionSolution {
    /**
     * 递归方法:
     * 每次检查当前字符,有几种可能性:
     * 1. p的下一个字符是*,那么首先考虑的可能性是S当前字符并不是p当前字符的通配出现, 也即初始假设p当前这个字符并没有在s中出现.
     *    1.1 初始尝试,p指针后移2位递归求个结果,如果true,那肯定直接返回成功
     *    1.2 初始尝试失败, 说明s当前字符需要和p当前字符匹配一下, 再递归一次, 看看结果. 如果还不行,那直接返回失败.
     * 2. p的下一个字符不是*, 那么有2个可能性: p当前是. 或者 普通字符.
     *    这两种情况下,都需要考虑和s当前字符的匹配情况,成功则指针后移,不成功则直接返回false
     * @param s 待匹配串
     * @param p 正则表达式
     * @return 是否匹配的结果
     */
    public static boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;

        if(p.length() == 1) {
            //这个返回的精妙之处在于,直接把对s的长度条件融入到了与条件里. 这个条件成立的时候,后面的条件才能拿来做最终的判断.
            //如果s的长度条件不满足,那么后面不用判断了,肯定是false的.
            //所以用了"&&",相当于以下2句的效果:
            //  if(s.length()==1) return p.charAt(0) == '.' || p.charAt(0) == s.charAt(0);
            //  else return false;
            return s.length() == 1 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));
        }

        //p.length()>1时, 看当前字符的下一个字符是什么了.
        if(p.charAt(1) == '*') {
            if(isMatch(s, p.substring(2))) return true;//初始假设
            else { // 初始假设失败, s当前字符必须和p当前字符匹配,才有可能成功
                // s.length() > 0 的意义上面讲过:
                // 如果s已经为空串了,又已知p除去当前2个通配字符以后还有字符和s不匹配, 那就不用比了, 现在肯定也不匹配.
                // 第二行的意义是, p当前还得是'.' 或者和s相同的字符
                // 第三行的意义是, s跳过当前字符后,和p匹配了
                // 以上三个条件都成立, 才能算最终可以匹配成功.
                // 否则均失败
                return s.length() > 0
                        && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))
                        && isMatch(s.substring(1), p);
            }
        }

        //p当前字符下一个字符不是*, 最好处理
        //匹配的条件是,
        //1.s不为空串,因为s若为空串, 而p当前字符不是.就是普通字符,必须有个字符和它匹配,那必然失败
        //2.p当前和s当前匹配
        //3.p和s分别后移一位,也最终匹配
        //1+2+3返回成功,才能算成功
        else {
            return s.length() > 0
                    && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))
                    && isMatch(s.substring(1), p.substring(1));
        }
    }

    public static void main(String[] args){
        System.out.println(isMatch("aabbbcd", "aab*bbbcd"));
    }
}
