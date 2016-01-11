package com.leetcode.number126;

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
     * 然后反向由endWord再DFS往回找,直到找到beginWord, 找到ladder
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(beginWord);
        wordList.add(endWord);
        /**
         * 由beginWord开始BFS
         * 加入队列, 然后枚举其全部变形词, 互相加入自己的邻接map
         * 同时, 变形词入队列
         */
        Map<String, Integer> distMap = new HashMap<>();//节点距离
        int length = -1;
        Queue<String> wordQ = new LinkedList<>();
        wordQ.offer(beginWord);
        //层序思想
        while(!wordQ.isEmpty()) {
            length++;
            int i = 0;
            int len = wordQ.size();
            while (i < len) {
                String thisWord = wordQ.poll();
                distMap.put(thisWord, length);//同一层的string,都有相同的起点距离
                List<String> adj = getAdjacent(thisWord, wordList);
                adjWordMap.put(thisWord, adj);
                for (String str : adj) {
                    if (!distMap.containsKey(str)
                            && !wordQ.contains(str)) {
                        wordQ.add(str);
                    }
                }
                i++;
            }
        }//while-end, get the adjmap: undirected graph

        /**
         * DFS由begin找到end的最短路径即可
         */
        List<String> tmp = new LinkedList<>();
        List<List<String>> rst = new ArrayList<>();
        ladderHelper(endWord, beginWord, tmp, rst, distMap);
        return rst;
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
    private static void ladderHelper(String endWord, String curWord, List<String> tmp,
                              List<List<String>> rst, Map<String, Integer> distMap) {
        tmp.add(curWord);
        if (curWord.equals(endWord)) {
            rst.add(new LinkedList<>(tmp));
            tmp.remove(tmp.remove(tmp.size() - 1));
            return;
        }

        List<String> adj = adjWordMap.get(curWord);//get the adj nodes
        for (String str : adj) {
            if (distMap.get(str) == distMap.get(curWord) + 1) {
                ladderHelper(endWord, str, tmp, rst, distMap);
            }
        }
        tmp.remove(tmp.size() - 1);
    }

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
                    String newWord = w1.substring(0, i) + ch
                            + w1.substring(i + 1);
                    if (wordList.contains(newWord)) {
                        rst.add(newWord);
                    }
                }
            }
        }//26 * 单词平均长度, 速度很快
        return rst;
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
        List<List<String>> rst = findLadders("hot","dog",wl);
    }
}

