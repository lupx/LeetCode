package com.leetcode.number173;

import java.util.Stack;

/**
 * 这个实现更好!
 * 构造函数很快, 不需要递归
 *
 * 同时,找next的时候,也基本可以算是O(1)时间.
 *
 * Created by PeixinLu on 15/11/29.
 */
public class BSTIteratorV2 {


    private Stack<TreeNode> path = new Stack<TreeNode>();

    public BSTIteratorV2(TreeNode root) {
        while(root!=null) {
            path.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !path.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = path.pop();
        if(node.right!=null) {
            TreeNode curr = node.right;
            while(curr!=null) {
                path.push(curr);
                curr = curr.left;
            }
        }
        return node.val;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
