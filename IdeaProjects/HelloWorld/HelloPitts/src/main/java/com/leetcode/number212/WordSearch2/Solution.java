package com.leetcode.number212.WordSearch2;

import java.util.*;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell,
 *    where "adjacent" cells are those horizontally or vertically neighboring.
 *    The same letter cell may not be used more than once in a word.
 * For example,
 *  Given words = ["oath","pea","eat","rain"] and board =
 *
 *        [
 *        ['o','a','a','n'],
 *        ['e','t','a','e'],
 *        ['i','h','k','r'],
 *        ['i','f','l','v']
 *        ]
 *  Return ["eat","oath"].
 *
 * n^3的算法很简单能得到,但是速度太慢!
 *
 * 回溯, 可以结合trie树来做:
 * 首先,把所有word加入trie树
 * 然后两层循环遍历board:
 *  每个位置作为一个prefix在trie树中看看有没有:
 *    如果有, 在trie树中精确找当前串, 如果有说明找到了, 返回true.  如果没有, 再DFS到临近格, 重复上面过程
 *    如果没有,直接返回false
 * 最终如果找到, 就把当前串加入rst
 * 实现细节: boolean[][] visited, 用来记录是否走过, 每次走完一趟后, 该数组全部置为false
 *    此外, 走过的格,只要return了, 也应该将visited置为false, 允许从其他的可能性上考虑
 *
 * beat 65.24%
 * Created by Peixin Lu on 15/12/25.
 */
public class Solution {
    static Trie trie;
    static boolean[][] visited;
    static List<String> rst;
    static Set<String> found;
    /**
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words) {
        trie = new Trie();
        found = new HashSet<>();
        for (String s : words) {
            trie.insert(s); // 所有word存入trie中
        }

        rst = new ArrayList<>();
        //两层循环遍历board, 对每个格search一遍trie
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                visited = new boolean[board.length][board[0].length];//重置
                StringBuilder sb = new StringBuilder();
                findHelper(board, i, j, sb);
            }
        }
        return rst;
    }

    /**
     * 递归方法: 从board[i][j]开始深搜
     * 注意: visited确保没有访问过, 同时不能走回头路
     * @param board
     * @param i
     * @param j
     */
    private static void findHelper(char[][] board, int i, int j, StringBuilder tmp) {
        tmp.append(board[i][j]);
        if (trie.startsWith(tmp.toString())) {
            visited[i][j] = true;
            if (trie.search(tmp.toString()) && !found.contains(tmp.toString())) {
                //彻底找到了
                rst.add(tmp.toString());
                found.add(tmp.toString());
//                tmp.deleteCharAt(tmp.length() - 1);
//                visited[i][j] = false;
//                return;
            }
            //还没找到, 到各个方向深搜
            if (i > 0 && !visited[i - 1][j]) findHelper(board, i - 1, j, tmp);
            if (i < board.length - 1 && !visited[i + 1][j]) findHelper(board, i + 1, j, tmp);
            if (j > 0 && !visited[i][j - 1]) findHelper(board, i, j - 1, tmp);
            if (j < board[0].length - 1 && !visited[i][j + 1]) findHelper(board, i, j + 1, tmp);
            visited[i][j] = false;//当前格作为对一个词的尝试全部结束, visited置为false
        }
        tmp.deleteCharAt(tmp.length() - 1);
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'s','e','e','n','e','w'},
                {'t','m','r','i','v','a'},
                {'o','b','s','i','b','d'},
                {'w','m','y','s','e','n'},
                {'l','t','s','n','s','a'},
                {'i','e','z','l','g','n'}
//                {'a','b'},
//                {'a','a'}
        };
//        String[] words = new String[]{"oath","pea","eat","rain"};
        String[] words = new String[]{"anda","anes","anesis","avener","avine","benda","bowl"};
        List<String> rst = findWords(board, words);
        for (String str : rst) {
            System.out.println(str);
        }
    }
}
class Trie {
    /**
     * Trie数的根
     */
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    // 从root节点开始迭代找各个层有没有对应的字符, 如有就继续, 如果没有, 给该层加对应字符子树
    public void insert(String word) {
        TrieNode thisNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            thisNode.insertChild(c);
            thisNode = thisNode.retrieveChild(c);
        }
        thisNode.stopHere = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode thisNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            thisNode = thisNode.retrieveChild(c);
            if (thisNode == null) return false;//说明没有对应字符的子树
        }
        return thisNode.stopHere;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode thisNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            thisNode = thisNode.retrieveChild(c);
            if (thisNode == null) return false;//说明没有对应字符的子树
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("a");
        trie.insert("bc");
        System.out.println(trie.search("a"));
        System.out.println(trie.startsWith("b"));
    }
}

class TrieNode {
    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];//26个孩子
        stopHere = false;
    }
    TrieNode[] children;
    boolean stopHere;
    /**
     * 当前节点增加新的孩子
     * 如果当前字符已经有对应的孩子节点, 则直接return
     * @param c 孩子节点对应的字符
     */
    public void insertChild(Character c) {
        if (children[c - 97] == null)
            children[c - 97] = new TrieNode();
    }

    /**
     * 获取字符c对应的孩子节点
     * @param c
     * @return
     */
    public TrieNode retrieveChild(Character c) {
        return children[c - 97];//可能为null, 说明没有找到对应字符c的子树
    }
}
