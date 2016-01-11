package com.leetcode.number114;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 *          5
 *         / \
 *        4   8
 *       /   / \
 *      11  13  4
 *     /  \    / \
 *    7    2  5   1
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * 回溯
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;
        List<Integer> tmp = new ArrayList<>();
        pathHelper(root, rst, tmp, sum);
        return rst;
    }

    /**
     * 每次把当前root加入tmp,
     *   如果是叶子节点, 就检查下sum是否为0了, 是的话, 加入rst,返回
     *   如果不是叶子节点, 那么左右子树, 哪个不空往哪边递归
     * 最后记得抹去root节点
     * @param root
     * @param rst
     * @param tmp
     * @param sum
     */
    private void pathHelper(TreeNode root, List<List<Integer>> rst, List<Integer> tmp, int sum) {
        tmp.add(root.val);
        if (sum - root.val == 0
                && root.left == null && root.right == null) {
            rst.add(new ArrayList<>(tmp));
            tmp.remove(tmp.size() - 1);
            return;
        }
        if (root.left != null) pathHelper(root.left, rst, tmp, sum - root.val);
        if (root.right != null) pathHelper(root.right, rst, tmp, sum - root.val);
        tmp.remove(tmp.size() - 1);
    }

    public static void main(String[] args) {

    }
}

