package com.leetcode.number208.DesignTrie;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * 其实就是构造了一个26叉树
 * beat 46.41%
 * Created by Peixin Lu on 15/12/25.
 */
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

public class Trie {
    /**
     * Trie数的根
     */
    private TrieNode root;

    public Trie() {
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

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");

