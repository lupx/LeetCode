package com.leetcode.number211.AddWord.StructureDesign;

/**
 * 实现一个字典, 同时'.'可以在搜索的时候匹配任意字符
 * 实际就是用trie树实现本体, 然后搜索算法进行改进
 * beat 49.71%
 * Created by PeixinLu on 15/12/25.
 */
public class WordDictionary {

    /**
     * Trie树的根
     */
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode thisNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            thisNode.insertChild(c);
            thisNode = thisNode.findChild(c);
        }
        thisNode.setStopSign(true);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if (word.equals("")) return false;
        return search(root, word);
    }

    private boolean search(TrieNode node, String word) {
        TrieNode thisNode = node;
        boolean rst = false;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (c == '.') {
                //匹配任意字符
                //从.位置开始,在后续找可能的匹配
                for (TrieNode n : thisNode.getChildren()) {
                    if (n != null) {
                        rst =  rst || search(n, word.substring(i + 1));
                    }
                }
                return rst;
            } else {
                thisNode = thisNode.findChild(c);
                if (thisNode == null) return false;//说明没有对应字符的子树
            }
        }
        return thisNode.getStopSign();
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("abc");
        wd.addWord("bcd");
        System.out.println(wd.search(".c."));
    }
}

class TrieNode {

    private TrieNode[] children;

    private boolean WORD_STOPS_HERE;

    //contructor of TrieNode
    public TrieNode() {
        children = new TrieNode[26];
        WORD_STOPS_HERE = false;
    }

    public boolean getStopSign() {
        return WORD_STOPS_HERE;
    }

    public void setStopSign(boolean b) {
        WORD_STOPS_HERE = b;
    }

    public void insertChild(Character c) {
        if (children[c - 97] == null)
            children[c - 97] = new TrieNode();
    }

    public TrieNode[] getChildren() {
        return children;
    }

    /**
     * 获取字符c对应的孩子节点
     * @param c
     * @return
     */
    public TrieNode findChild(Character c) {
        return children[c - 97];//可能为null, 说明没有找到对应字符c的子树
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");