package com.leetcode.number100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * 两个树要相等, 左右子树链要都相同, 且对等节点上的val也要相等
 * 方案两个:
 * (1)层序遍历p, 然后看看序列是否和q层序遍历完全相同.(前序,中序,后序也是一个道理,中序最简单)
 * (2)递归, 每次求当前节点左右子树是否same,如果same,再比较当前节点是否same,如果都true,返回true给上层;
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 方案一: stack解法
     * OJ 时间 1ms
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q == null) return false;
        if (p == null) return false;

        //模拟一个队列
        List<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(p);
        queue.add(q);
        int index = 0;
        while (index < queue.size()) {
            TreeNode node1 = queue.get(index);
            TreeNode node2 = queue.get(index + 1);
            if (node1.val != node2.val) return false;
            //node1和node2必须同有左子,或者同无左子
            if (node1.left != null && node2.left != null) {
                queue.add(node1.left);
                queue.add(node2.left);
            } else if (node1.left == null
                    && node2.left == null) {

            } else {
                return false;
            }
            if (node1.right != null && node2.right != null) {
                queue.add(node1.right);
                queue.add(node2.right);
            } else if (node1.right == null
                    && node2.right == null) {

            } else {
                return false;
            }
            index += 2; //一次取两个
        }
        return true;
    }

    /**
     * 方案二: 递归求解
     * 速度也很快, 0ms
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreev2(TreeNode p, TreeNode q) {
        //下面三行就是基准条件
        if (p == null && q == null) return true;
        if (p != null && q == null) return false;
        if (p == null) return false;

        if (p.val != q.val) return false;

        //左子树比一比, 右子树比一比
        return isSameTreev2(p.left, q.left)
                && isSameTreev2(p.right, q.right);
    }


    public static void main(String[] args) {
    }
}

