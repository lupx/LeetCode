package com.leetcode.number68;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 *
 *
 * Created by Peixin Lu on 15/10/14.
 */
public class Solution {

    /**
     * 分析: 按顺序贪心填词.
     * 填词的要求:
     * 尽量多地把词塞进一行里, 每个词间最少要有一个空格
     * 词与词之间的空格要尽量平均
     *
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < words.length; i++) {
            String thisWord = words[i];
            int thisWlen = thisWord.length();
            int wcount = 1;//最终当前一行可以放的词的个数
            for (int j = i + 1; j < words.length; j++) {
                thisWlen += words[j].length();
                wcount++;
                if (thisWlen + (wcount - 1) <= maxWidth) {
                    continue;
                } else {
                    //已超, 那么当前一行就只能放 i -> j-1的词
                    thisWlen -= words[j].length();
                    wcount--;
                    break;
                }
            }
            //词个数为wcount个, 所以间隔有wcount-1个.
            //词总长度为thisWlen
            //所以可以分配的空间有 maxWidth - thisWlen
            //分配算法:
            //先拿总空间除以总间隔数, 得到平均每个间隔最少能分到的空格数
            //再对余数做循环, 每个间隔分到一个, 直到余数用完
            StringBuilder sb = new StringBuilder();
            if (wcount == 1 || (i + wcount) == words.length) {
                if (wcount == 1) {
                    //特殊情况
                    if (thisWord.length() == maxWidth) {
                        //直接填入
                        result.add(thisWord);
                        continue;
                    }
                }
                for (int k = 0; k < wcount; k++) {
                    sb.append(words[i + k]).append(" ");
                }
                //需要补空格
                int rest = maxWidth - thisWlen - wcount;
                if (rest < 0) {
                    //还需要减掉后面多的1个空格
                    sb.deleteCharAt(sb.length() - 1);
                }
                for (int k = 0; k < rest; k++) {
                    sb.append(" ");
                }
                result.add(sb.toString());
                i += wcount - 1;
                continue;
            }
            int[] gaps = new int[wcount - 1];
            int base = (maxWidth - thisWlen) / gaps.length;
            int remain = (maxWidth - thisWlen) % gaps.length;
            for (int k = 0; k < gaps.length; k++) {
                if (remain != 0) {
                    gaps[k] = base + 1;
                    remain--;
                } else {
                    gaps[k] = base;
                }
            }

            for (int k = 0; k < wcount - 1; k++) {
                sb.append(words[i + k]);
                for (int x = 0; x < gaps[k]; x++) {
                    sb.append(" ");
                }
            }
            sb.append(words[i + wcount - 1]);
            result.add(sb.toString());
            i += wcount - 1;
        }
        return result;
    }

    public static void main (String[] args) {
        String[] words = new String[]{
                " "," ",""
//                "justification.",
//                "This", "is", "an",
//                "example", "of", "text", "is","b","appple"
        };
        List<String> list = fullJustify(words, 3);
        for (String str: list) {
            System.out.println(str);
        }
    }

}
