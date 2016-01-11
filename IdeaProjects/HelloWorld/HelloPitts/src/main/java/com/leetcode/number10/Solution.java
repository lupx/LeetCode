package com.leetcode.number10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Regular Expression Matching
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.

 * The matching should cover the entire input string (not partial).

 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)

 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 *
 * 解释:
 * (1)p中若无*无.,则p equals s, 才能true
 * (2)p中有*有., *前字符,在s中保证相对位置的情况下可以无限出现(0-无穷个);
 *    p中.可以代替任意字符,但只能代替一次
 *
 * 有几个特殊情况, 验证了一下, 确实是需要考虑的:
 * (1)*后是*, 那么前*当做普通字符看待,s中可以有无数个*
 * (2).后是*, 那么s中可以随意无限匹配.*
 * (3)*单独出现, *就是个普通字符,s中这个位置,也必须是个*
 *
 * Created by Peixin Lu on 15/9/14.
 */
public class Solution {
    public static boolean isMatch(String s, String p) {
        String tmp = p.replaceAll("\\*","");
        String tmp2 = tmp.replaceAll("\\.","");

        if(s == "") { //s为空串情况
            if(p.equals("**") || p == "") return true; //考虑为什么p有2个*就可以匹配?
            return false;//其他情况全部false
        }

        if(p == "") { //p为空串情况
            return false;//由于s同样为空串的情况,上面已经涵盖,这里就直接返回false了
        }

        if(tmp2.equals(p)) {
            if(s.equals(p)) return true; //p中无通配符*或.,那么p必须和s相同,才能true
            else return false;
        }

//        if(tmp.length() == 0) { //p中全*
//            if(p.length() == 1) {
//                if(s.replaceAll("\\*","").length() == 0 && s.length() == 1) {
//                    return true; //s,p都只有一个*, 这个居然也是成立的
//                } else {
//                    return false;
//                }
//            }
//            if(p.length() == 2 && s == "") return true;
//            else return false; //p长度为2,2个*,且s中有字符, false
//        }

        //经过上面特殊情况的过滤, 走到这里s,p肯定都有字符,正式比较
        //p中有*或有.的情况:
        //*前字符可在保证相对位置的情况下无限出现
        //*前可以是'.',但不是能*. 遇到两个*接连出现可以直接false
        //两个指针,i指向s,j指向p, 分别推移, 综合分析即可
//        int i = 0, j = 0;
//        while(true) {
//            //每一轮需要判断i==s.length()的情况该如何处理, 还需要判断j==p.length()的情况该如何处理.
//            //这两种处理最终都得break,保证不死循环.
//            if(i == s.length() - 1 ) {
//                //j追赶或i回撤机制:
//                //说明,截止目前,s都是匹配的.那么决定S最终是否匹配的,要看p剩余部分中有没有可能令s不匹配的字符.
//                //比如,如果p剩余., 或者.*都是可行的. 如果遇到这些情况, j+1 或者 j+2 continue; 此为j追赶.
//                //有一种情况, 如果当前j指向了一个普通字符,它的后两位都是**,并且后三位又是普通字符.那么第一个*修饰当前普通字符,第二个*,就要考虑把i回撤,来匹配这个落单的*
//                //如果p剩余的遇到了***,那么就要考虑第一个*在s中必须出现一次.
//                //
//            }
//            char c1 = s.charAt(i);
//            char c2 = p.charAt(j);
//
//        }
        // 先对p做处理, 分出'*二元组'和落单组:
        // *二元组: 二个元素,并且第二个元素为*,  这种就叫'*二元组'.
        // 落单组: 非二元组,比如一个.,一个*,几个普通字符.一个.和几个普通字符, 这些都是落单组.
        // 遍历p,找出落单组,落单组的位置确定后,其余位置自然就是二元组
        List<String> strlist = new ArrayList<String>();
//        List<SingleGroup> slist = new ArrayList<SingleGroup>();
//        StringBuilder starsb = new StringBuilder();
//        SingleGroup sg = new SingleGroup();
        List<Integer> posList = new ArrayList<Integer>();

//        boolean addstar = false;
        boolean addsingle = false;

        String tmpstar;//临时*二元组
        String tmpsingle;//临时落单组
        boolean hasPoint = false;
        int[] points = new int[p.length()];
        int pointscount = 0;
        for(int i = 0;i<p.length(); ) {//i的推移需要人为控制
            char c = p.charAt(i);
            // 当前不管是什么,都得看后一个是不是*,如果是*,是二元组,直接i+2 continue;
            // 如果后一个不是*, 判断是否当前就在数落单组,如果是,i+1. 如果不是,boolean置为true,i+1 continue
            // 根据前两个字符情况,确定两个boolean量的初始化
            if(i == p.length()-1){
                //i为最后一个字符
                posList.add(i); //当前字符肯定属于落单组. 考虑为什么?
                if(posList.size()%2!=0) {
                    if(p.charAt(i) != s.charAt(s.length()-1)) {
                        //最后一个落单组自成一组
                        return false;
                    }
                }
                break;
            }
            if(c == '.') {
                hasPoint = true; //后面有用
                points[pointscount] = i;
                pointscount++;
            }
            if(p.charAt(i+1) == '*') {
                if(addsingle) addsingle = false;
                if(i > 0 && p.charAt(i-1) != '*') {
                    //i-1位置为落单组最后一个字符
                    posList.add(i);//记录了落单组结束位置的下一个位置
                }
                i += 2;//i前跳2格
            } else {
                if(!addsingle){
                    addsingle = true;
                    posList.add(i); //记录了起始位置
                }
                i++;
            }
        } //得到落单组数组,记录了各个落单组的起始-结束位置.可能包含最后一个字符的落单字符.

        // 很明显,落单组将严格把s的格式固定下来. s中必须严格满足落单组的要求,才能继续匹配剩下内容.
        // 所以,先在s中匹配落单组, 如果都能匹配成功,那么s必然被分成了几个部分,然后各自部分再与该落单组左右进行匹配.
        // 如果没有落单组,那么就简单了,如果有点,肯定匹配成功.如果无点,挨个匹配考虑即可,把s匹配完就成功了
        if(posList!=null&&posList.size()==0){
            //无落单组
            if(hasPoint) {return true;} //有'.',肯定通配,考虑为什么?
            else {
                //无点.,逐个比较考虑
                int i = 0, j = 0;
                while(i < s.length()) {
                    if(j > p.length()) {return false;} //考虑为什么?说明找遍了p都没有找到和i当前字符相同的字符,那么肯定不匹配
                    char x = s.charAt(i);
                    char y = p.charAt(j);
                    if(x != y) {
                        j += 2;
                        continue;
                    } else {
                        i++;
                    }
                }
                return true;
            }
        } else {
            //有落单组
            Map<String, List<Integer>> map
                    = new HashMap<String, List<Integer>>();//保存各个落单组在s中各个可能相同串的起始位置,所有可能的位置都写入数组
            int i = 0;
            List<Integer> l = new ArrayList<Integer>();
            while(i < posList.size()) {
                int start = posList.get(i);
                int end = -1;
                if((i+1)<posList.size()) {
                    end = posList.get(i+1);
                }
                if(end == -1){
                    break;
                }
                for(int j = 0; j<s.length(); j++){
                    if(j+(end-start)>s.length()) break;
                    if(s.substring(j,j+(end-start)).equals(p.substring(start,end))) {
                        l.add(j);
                    }
                }
                if(l.size()==0) {return false;} //从头到尾没找到和这个落单串匹配的
                map.put(p.substring(start,end), l); //保存了所有的可能位置
            }
            //到这里,所有的落单串至少在s中都能匹配到,我们做最后的努力, 对不同的区间,分别判断


        return true;
        }

    }

    public static void main(String[] args) {
        System.out.println("aaaa..aaa".replaceAll("\\.", ""));
    }
}
