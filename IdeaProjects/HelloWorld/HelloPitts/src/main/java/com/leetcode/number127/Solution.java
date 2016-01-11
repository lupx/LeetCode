package com.leetcode.number127;

import java.util.*;

/**
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {
    /**
     * 每个词的邻接map, 这货是整个word-graph的基石
     */
    static Map<String, List<String>> adjWordMap = new HashMap<>();
    /**
     * 先由beginWord开始BFS, 建立图: 生成邻接map
     * 然后反向由endWord再DFS往回找, 直到找到beginWord, 找到ladder
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return int
     */
    public static int findLadders(String beginWord, String endWord, Set<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        wordList.add(beginWord);
        wordList.add(endWord);

        /**
         * 由beginWord开始BFS
         * 加入队列, 然后枚举其全部变形词
         * 同时, 变形词入队列
         */
        Map<String, Integer> distMap = new HashMap<>();//节点距离
        int length = 0;
        Queue<String> wordQ = new LinkedList<>();
        wordQ.offer(beginWord);
        distMap.put(beginWord, 1);
        //层序思想
        while(!wordQ.isEmpty()) {
            length++;
            int i = 0;
            int len = wordQ.size();
            while (i < len) {
                String thisWord = wordQ.poll();
                List<String> adj = getAdjacent(thisWord, wordList);
                for (String str : adj) {
                    if (distMap.containsKey(str)) continue;
                    if (str.equals(endWord)) return length + 1;
                    wordQ.add(str);
                    distMap.put(str, length + 1);
                }
                i++;
            }
        }
        return 0;
    }

    /**
     * 找邻接word, 加入tmp, 递归试试
     * 正向DFS(当然也可反向):
     * beginWord距离置为0, 每个邻接点距离均在当前节点距离上+1, 并加入map, 然后递归
     * 每节点不能往回走, 不能往距离相同节点上走.
     * @param endWord
     * @param curWord 当前准备加入tmp的节点
     * @param tmp
     * @param rst
     * @param distMap
     */
//    private static void ladderHelper(String endWord, String curWord, List<String> tmp,
//                              List<List<String>> rst, Map<String, Integer> distMap) {
//        tmp.add(curWord);
//        if (curWord.equals(endWord)) {
//            rst.add(new LinkedList<>(tmp));
//            tmp.remove(tmp.remove(tmp.size() - 1));
//            return;
//        }
//
//        List<String> adj = adjWordMap.get(curWord);//get the adj nodes
//        for (String str : adj) {
//            if (distMap.get(str) == distMap.get(curWord) + 1) {
//                ladderHelper(endWord, str, tmp, rst, distMap);
//            }
//        }
//        tmp.remove(tmp.size() - 1);
//    }

    /**
     * 从wordList中找到w1的所有邻接词,并返回
     * @param w1
     * @param wordList
     * @return
     */
    private static List<String> getAdjacent(String w1, Set<String> wordList) {
        List<String> rst = new ArrayList<>();
        for (int i = 0; i < w1.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != w1.charAt(i)) {
                    String newWord = replace(w1, i ,ch);
                    if (wordList.contains(newWord)) {
                        rst.add(newWord);
                    }
                }
            }
        }//26 * 单词平均长度, 速度很快
        return rst;
    }

    private static String replace(String word, int index, char ch) {
        char[] chars = word.toCharArray();
        chars[index] = ch;
        return new String(chars);
    }

    public static void main(String[] args) {
        Set<String> wl = new HashSet<>();
        wl.add("hot");
        wl.add("pot");
        wl.add("tot");
        wl.add("dot");
        wl.add("hog");
        wl.add("hop");
        wl.add("cog");
        wl.add("dog");
        System.out.println(findLadders("hot","dog",wl));
    }
}

