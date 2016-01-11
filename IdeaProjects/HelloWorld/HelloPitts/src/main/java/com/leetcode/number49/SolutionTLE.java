package com.leetcode.number49;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * For the return value, each inner list's elements must follow the lexicographic order.
 * 输出必须保持字典序
 * All inputs will be in lower-case. 所有输入都是小写
 * Created by Peixin Lu on 15/10/11.
 */
public class SolutionTLE {


    /**
     * 遍历一遍, 把相同长度的放入一个组, 然后遍历这个组,对同长度的调用helper解决
     *
     * 该算法会TLE!
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strs == null || strs.equals("")) return result;

        Map<Integer, List<String>> lenMap = new HashMap<Integer, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            if (!lenMap.containsKey(strs[i].length())) {
                List<String> l = new LinkedList<String>();
                l.add(strs[i]);
                lenMap.put(strs[i].length(), l);
            } else {
                List<String> l = lenMap.get(strs[i].length());
                if (strs[i].length() == 0) {
                    l.add("");
                } else {
                    boolean inserted = false;
                    for (int x = 0; x < l.size(); x++) {
                        if (l.get(x).charAt(0) < strs[i].charAt(0)) {
                            continue;
                        } else {
                            l.add(x, strs[i]);
                            inserted = true;
                            break;
                        }
                    }
                    if (!inserted) {
                        l.add(strs[i]);
                    }
                }
            }
        }//按长度分组完毕

        //获取map集合中的所有键的Set集合
        Set<Integer> keySet = lenMap.keySet();
        //有了Set集合就可以获取其迭代器，取值
        Iterator<Integer> it = keySet.iterator();
        while (it.hasNext()) {
            List<String> samelen = lenMap.get(it.next());
            helper(samelen, result);
        }

        return result;
    }

    /**
     * 把strs存入map
     * 遍历strs, 首先定义第一个词为当前词,当前词各位存入thisMap
     * 从当前词下一个词开始,对每个词thatStr,
     *    各位字符在thisMap中找一下,如果能找到,存入thatMap
     *    如果找不到直接break
     *    最终,各位走完后,比较下thatMap和thisMap的长度,长度若相同,则认为符合条件.
     *    总map删除thatStr
     *    continue.
     *
     * 此算法未排序
     *
     * @param strs
     * @return
     */
    public static void helper(List<String> strs, List<List<String>> result) {
        Map<String, Integer> totalMap = new HashMap<String, Integer>();

        for (String str: strs) {
            totalMap.put(str, 1);
        }

        for (int i = 0; i < strs.size(); i++) {
            if (totalMap.containsKey(strs.get(i))) {
                String thisStr = strs.get(i);
                List<String> sameGroup = new LinkedList<String>();//比较好插入
                if (thisStr.length() == 0) {
                    //当前词是""
                    //找出所有""加入sameGroup,然后continue.
                    sameGroup.add(thisStr);
                    for (int j = i + 1; j < strs.size(); j++) {
                        if (strs.get(i).length() == 0) {
                            sameGroup.add(strs.get(i));
                        }
                    }
                    result.add(sameGroup);
                    continue;
                }
                sameGroup.add(thisStr);
                Map<Character, Integer> thisMap = new HashMap<Character, Integer>();
                for (int k = 0; k < thisStr.length(); k++) {
                    thisMap.put(thisStr.charAt(k), 1);
                }
                for (int j = i + 1; j < strs.size(); j++) {
                    String thatStr = strs.get(j);
                    Map<Character, Integer> thatMap = new HashMap<Character, Integer>();
                    for (int k = 0; k < thatStr.length(); k++) {
                        if (thisMap.containsKey(thatStr.charAt(k))) {
                            thatMap.put(thatStr.charAt(k), 1);
                        } else {
                            break;
                        }
                    }
                    if (thisMap.size() == thatMap.size()) {
                        //thatStr和thisStr是同一组
                        totalMap.remove(thatStr);
                        sameGroup.add(thatStr);
                    }
                }
                result.add(sameGroup);
            }
        }
    }


    public static void main(String[] args) {
        String[] strs = new String[]{"aa", " ", "aaa", " ", "", "bbb"};
        List<List<String>> result = groupAnagrams(strs);
        for (List<String> l : result) {
            StringBuilder sb = new StringBuilder();
            System.out.println(l.size());
            for (String s : l) {
                sb.append(s).append("|");
            }
            System.out.println(sb.toString() + "\n");
        }
    }
}
