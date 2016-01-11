package com.leetcode.number10;

/**
 * Created by PeixinLu on 15/9/15.
 */
public class SolutionNew {

    public static boolean isMatch(String s, String p) {
        String tmp = p.replaceAll("\\*","");
        String tmp2 = tmp.replaceAll("\\.","");

        if(s == "") { //s为空串情况
            if(p == "" || (p.length() == 2 && p.charAt(1) == '*')) return true;
            return false;//其他情况全部false
        }

        if(p == "") { //p为空串情况
            return false;//由于s同样为空串的情况,上面已经涵盖,这里就直接返回false了
        }

        if(tmp2.equals(p)) {
            if(s.equals(p)) return true; //p中无通配符*或.,那么p必须和s相同,才能true
            else return false;
        }

        int i = 0, j = 0;
        while(i<s.length() && j<p.length()) {
            char c1 = s.charAt(i);
            char c2 = p.charAt(j);
            boolean dfsflag = false;
            if(c1 == c2) {
                dfsflag = isMatch(s, p, i, j);
                if(dfsflag) return true;
                else {

                }
            }
        }
        return true;
    }

    /**
     * 给定一个s起点和p起点, 确定从该起点起的剩余匹配情况
     * @param s
     * @param p
     * @param s_start
     * @param p_start
     * @return
     */
    public static boolean  isMatch(String s, String p, int s_start, int p_start) {
        int i = s_start, j = p_start;
        while (i < s.length() && j < p.length()) {
            char c1 = s.charAt(i);
            char c2 = p.charAt(j);
            if(j == p.length() -1 ) {
                //j为当前最后一个字符, 所以不需要再考虑j+1,直接看匹不匹配即可
                if(c1 == c2) {
                    if(i == s.length() - 1) {
                        return true;
                    } else {
                        return false;//i不是最后一个,那么s肯定无法匹配p
                    }
                } else {
                    // j为当前p最后一个字符, 且c1!=c2
                    // 此时有几个可能性
                    // (1) c2 == 普通字符或者*,那么应该直接判错
                    // (2) c2 == ., 那么看看i是不是最后一个字符.  如果是, true; 如果不是, false
                    if(c2 != '*' && c2 != '.') {
                        return false;
                    } else if(c2 == '*') {
                        return false;
                    } else {//c2 == '.'
                        if(i == s.length() - 1) return true;
                        else return false;
                    }
                }
            }

            //上面直接过滤了所有j+1越界的情况,下面可以放心使用j+1
            if(c1 == c2) {
                //相等情况:
                if(p.charAt(j + 1) == '*') {
                    i++;
                } else {
                    i++;
                    j++;
                }
            } else {
                if(c2 == '.' && p.charAt(j + 1) == '*') {
                    //i stays here
                    int k = j + 2;
                    while (k < p.length()) {
                        if(k == p.length() - 1) {//k is the last of p
                            if(p.charAt(k) == '.') {//last is a '.'
                                return true;
                            } else {
                                if(s.charAt(s.length() - 1) == p.charAt(k)) {
                                    //* 或 普通字符, s最后一个字符只要和其相等就可匹配
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                        //上面把k为最后一个元素已考虑到,下面可安全使用k+1
                        if(p.charAt(k + 1) != '*') {//k is not followed by a *
                            j = k;
                            break;//j往前推移找到了奇点,所以j推移到奇点,然后比较i和当前j
                        } else {//k is followed by a *
                            k += 2;
                        }
                    }
                    if(k > p.length() - 1) {
                        //natural end of the while, so
                        return true;
                    }
                    continue; //i前置到落单字符,重新continue,开始扫描
                } else if(p.charAt(j) == '.' && p.charAt(j + 1) != '*') {//单独.
                    i++;
                    j++;
                } else if(p.charAt(j) == '*' && p.charAt(j + 1) != '*') {//后无*,说明为落单字符
                    return false;
                } else if(p.charAt(j) == '*' && p.charAt(j + 1) == '*') {//后连*
                    j += 2;
                } else if(p.charAt(j + 1) == '*'){
                    //普通字符后跟*
                    j += 2;
                } else {//仅为普通字符
                    return false;
                }
            }
        }

        //while走完, 说明是自然走完, 还是要收尾捡漏
        //分析一下: p提前走完,而i还在半路,只能说明一个问题, p中无'.'且走的这一路再没碰到任何奇点字符,p最后一个字符也不可能是'.'
        //也即, p剩余部分基本都是 $*这种二元组组成. 那么如果s还剩余, 说明i没有再前移, 进一步说明s[i]一直没有在p中找到相等字符.
        //So, 这个时候必定无法匹配!
        while(i < s.length()) {
            //p已无字符,s还有
            return false;
        }

        //s先走完, p还剩余, 得分析看情况.
        int head = j;
        while(j < p.length()) {
            //检查p剩余有无落单字符
            if(j == p.length() - 1) {
                // j指向p当前最后一个字符,肯定是落单字符, 这里有个特殊情况:
                // 当前字符和之前s略过的字符相同, 也即和上面循环刚结束的时候j所指元素相同,那么s中略过的元素少略过一位不就能匹配了么!?
                if(p.charAt(j) == p.charAt(head) && j!=head) {
                    return true;
                }
                //否则,不等的话,和i略过元素肯定是不同的了!
                //或者到达这个循环的时候,j就已经指向奇点了 testcase:("a", ".*..")
                return false;
            }
            if(p.charAt(j + 1) != '*') {
                return false; //在结尾前找到奇点,直接false
            } else {
                j += 2;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("abbbcd", "ab*bbbcd"));

        //try this ("abbbcd", "ab*bbbcd") ... Need Back Track!
    }
}
