package com.leetcode.number30;

import java.util.*;

/**
 *
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * You should return the indices: [0,9].
 * (order does not matter). 顺序不重要!
 *
 * 该题学到的最牛逼的点就是java8里新提出的 对Map的改进:
 * map.put(K, getOrDefault(K, 0) + 1);
 * 上面这句的意思就是,把k存入map.存的时候呢先查一下有没有k,如果有就+1, 如果没有就返回0,然后再+1
 *
 * Created by PeixinLu on 15/10/1.
 */
public class Solution {

    /**
     * 递归:
     * 遍历words, 作为前缀判断s是否符合前缀. 如果全部不符合, s.substring(wordlen)继续递归
     * 如果符合,words去掉符合的词生成剩余词的arrayList, 然后s.substring(wordlen)和arrayList递归
     * 只要返回false, 那么s.substring(wordlen)继续递归
     * 这个解法会TLE!
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();

        /**
         * 显然, s长度小于words的时候,直接返回空list
         */
        int length = words.length;
        int wordlen = words[0].length();
        if(s.length() < length * words[0].length()) return result;

//        List<String> wordlist = Arrays.asList(words);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String word: words) {
            map.put(word, map.getOrDefault(word , 0) + 1);
        }
        for(int i = 0; i <= s.length() - words.length * wordlen; i++) {
            if(canFindAny(s.substring(i), map, wordlen, length)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 给一个s, 判断是否能够在words中找到任意一个词作为其前缀,只要满足,就true
     * 如果出现不满足,就false
     *
     * 该方法的巧妙点在,不需要从words中挨个找词去匹配s
     *
     * 而是从s中,按wordlen找词出来看看words里面有没有:
     * (1)如果有,就加入当前记录map里
     * (2)如果没有,直接false
     *
     * 最后,循环结束后看当前map和wordsmap里各个词的个数是否一样.
     * 如果一样,则认为符合,返回true
     * 如果不一样,则认为false
     * 此外,还在每次加入当前map后,检查了当前词的个数会不会已经超出了map里的个数.
     * 这样,可以加快速度
     *
     * @param s
     * @param map
     * @return
     */
    public static boolean canFindAny(String s, Map<String, Integer> map, int wordlen, int wordscount) {
        Map<String, Integer> thisMap = new HashMap<String, Integer>();
        for(int i = 0; i < wordscount; i++) {
            String word = s.substring(i * wordlen, (i + 1) * wordlen);
            if(map.containsKey(word)) {
                thisMap.put(word, thisMap.getOrDefault(word, 0) + 1);
                if(thisMap.get(word) > map.get(word)) break;
            } else
                break;
        }
        if (thisMap.equals(map)) return true;
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"a", "a"};

        List<Integer> l = findSubstring("aaa", words);
        for(Integer i : l) {
            System.out.println(i);
        }
    }
}
