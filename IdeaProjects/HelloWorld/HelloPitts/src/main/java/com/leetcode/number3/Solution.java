package com.leetcode.number3;

import java.util.*;

/**
 * Created by PeixinLu on 15/8/30.
 */
public class Solution {
    /**
     * Given a string, find the length of the longest substring which is repeated several times and has no repeating char within.
     * For example, the longest substring without repeating letters for "abcdaabcbb" is "abc", which the length is 3.
     * For "bbbbb" the longest substring is "b", with the length of 1.
     * Time Complexity = O(n^3)
     * @param s
     * @return
     */
    public static int lengthOfLongestRepeatingStringWithoutRepeatingCharacter(String s) {
        if(s==null) return 0;
        char[] a = s.toCharArray();
//        Character[] aa = new Character[a.length];
//        for(int i=0;i<a.length;i++){
//            char x = a[i];
//            aa[i] = new Character(x);
//        }
        //we get the Character[] aa
        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();
        List<Integer> ls;
        for(int i=0;i<a.length;i++){
            ls = map.get(a[i]);
            if(ls!=null){
                ls.add(i);
                map.remove(a[i]);
                map.put(a[i],ls);
            } else {
                ls = new LinkedList<Integer>();
                ls.add(i);
                map.put(a[i],ls);
            }
        }//we get the map<char, [index1,index2,....indexn]>

        int head=0;//pattern's head
        int match;//match's head
        int current;// pattern's cursor
        int mcurrent;//match's cursor
        int maxlen=1;
        int thisLen;
        while(head<a.length){
            ls = map.get(a[head]);//absolutely will get it
            if(ls.size()!=0&&ls.size()==1){
                head++;
                continue;
            }
            ls.remove(0);// actually the aa[head], so we don't need it
            int count =0;
            while(count<ls.size()){
                match = ls.get(count);
                current = head;
                mcurrent = match;
                thisLen=1;
                while(true){
                    current++;
                    mcurrent++;
                    if(mcurrent<a.length&&a[current]==a[mcurrent]
                            &&a[current]!=(a[head])){
                        thisLen++;
                        if(thisLen>maxlen) maxlen = thisLen;
                    } else {
                        break;
                    }
                }
                count++;
            }//all same chars have been processed
            head++;
        }
        return maxlen;
    }

    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
     * For "bbbbb" the longest substring is "b", with the length of 1.
     * Time Complexity = O(n^2)
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s==null) return 0;
        char[] a = s.toCharArray();
        Map<Character,Integer> met = new HashMap<Character,Integer>();
        int tail = 0;
        int current = 0;
        int maxlen = 0;
        int thislen = 0;
        int tmp;
        while(current<a.length){
            if(met.containsKey(a[current])&&met.get(a[current])>=tail){
                //exists and between the tail and current, means it is a duplicated one!
                tmp = tail;
                tail = met.get(a[current])+1;
                if(tail == current){
                    thislen = 1;
                } else {
                    thislen = thislen - (met.get(a[current]) - tmp);
                }
                met.put(a[current],current);
                current++;
            } else {
                met.put(a[current], current);
                thislen++;
                if(maxlen<thislen) maxlen = thislen;
                current++;
            }
        }
        return maxlen;
    }

    public int lengthOfLongestSubstring_2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<Character>();

        int leftBound = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
                    set.remove(s.charAt(leftBound));
                    leftBound ++;
                }
                leftBound ++;
            } else {
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }

        return max;
    }

    public static void main(String[] args){//main method
//        String s = "apzivnhwqapyttsmaboaqhcqn";
//        String s = "aaaabcdefggg";
        String s = "bbbabbb";
//        System.out.println(lengthOfLongestRepeatingStringWithoutRepeatingCharacter(s));
        System.out.println(lengthOfLongestSubstring(s));
    }
}
