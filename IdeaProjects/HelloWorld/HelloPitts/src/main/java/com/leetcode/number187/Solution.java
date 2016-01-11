package com.leetcode.number187;

import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * beat 68.64%
     * @param s
     * @return
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> rst = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String thisStr = s.substring(i, i + 10);
            if (!set.contains(thisStr)) {
                set.add(thisStr);
            } else {
                if (!rst.contains(thisStr)) {
                    rst.add(thisStr);
                    set.remove(thisStr);
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        List<String> rst = findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCAAAAACCCCCAAAAA");
        for (String str : rst) {
            System.out.println(str);
        }
    }

}

