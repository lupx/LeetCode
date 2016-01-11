package com.leetcode.number93;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * Created by Peixin Lu on 15/10/22.
 */
public class Solution {

    /**
     * 返回所有可能的ip形式.
     * 首先, s为空,返回空
     * 其次, ip长度最大为12位,如果s.length>12, 返回空
     * 然后才有可能返回合法的ip地址.
     * 另外, 此题不能用DP, 因为是要求返回所有可能的具体方案.
     * 可以用回溯来做, 具体地, 用递归.
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() == 0
            || s.length() > 12) return result;
        String tmp = new String();
        addressHelper(s, tmp, result, 0);
        return result;
    }

    /**
     *
     * @param s
     * @param tmp
     * @param result
     * @param already 已经拼了几位了
     */
    public static void addressHelper(String s, String tmp, List<String> result, int already) {
        if (s.equals("") && already == 4) {
            result.add(new String(tmp));
        }
        if (already == 4) {
            //already已经是4了, 但是tmp的长度没有达到预想,说明方案有问题
            return;
        }
        //若长度不够, 直接返回
        if (already == 1) {
            //剩至少3位, 最大9位
            if (s.length() < 3 || s.length() > 9) return;
        }
        if (already == 2) {
            //剩至少2位, 最大6位
            if (s.length() < 2 || s.length() > 6) return;
        }
        if (already == 3) {
            if (s.length() == 0 || s.length() > 3) return;
        }

        //截取当前s前1,2,3位,分别看看当前是否可行,若可行递归之
        //若不可行,continue
        for (int i = 1; i <= 3 && i <= s.length(); i++) {
            String curStr = s.substring(0, i);
            if (curStr.length() > 1 && curStr.charAt(0) == '0') {
                break;//此方案肯定走不通
            }
            int cur = Integer.parseInt(curStr);//safe here
            if (cur <= 255) {
                //此划分可行
                StringBuilder sb = new StringBuilder(tmp);
                sb.append(curStr);
                if (already != 3) {//第四位的末尾不需要.
                    sb.append(".");
                }
                addressHelper(s.substring(i), sb.toString(), result, already + 1);
            }
        }
    }

    public static void main(String[] args) {
        List<String> list = restoreIpAddresses("0000");
        for (String s : list) {
            System.out.println(s);
        }
    }
}

