package com.leetcode.number95;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 * Created by Peixin Lu on 15/10/22.
 */
public class Solution {


    /**
     * 生成所有方案, 肯定不用DP
     * BST的特点:
     * 左子树上所有元素小于根, 右子树上所有元素大于根
     * 递归回溯:
     * 每次从1-n中选一个数 k ,然后用k作为root:
     *     左指针指向(1->k-1)递归求解的结果;
     *     右指针指向(k+1->n)递归求解的结果
     * 执行完, root加入result-list中
     * 最后返回result.
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n <= 0) {
                result.add(null);
                return result;
        }
        return treeHelper(n, 1, n);
    }

    /**
     * 生成从start到end之间的n个元素的BST
     * 生成成功后, root加入result中.
     * @param n
     * @param start
     * @param end
     */
    private static List<TreeNode> treeHelper(int n, int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (n == 0) {
            result.add(null);
            return result;
        }
        if (n == 1) {
            TreeNode node = new TreeNode(start);
            result.add(node);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTree = treeHelper(i - start, start, i - 1);
            List<TreeNode> rightTree = treeHelper(end - i, i + 1, end);
            for (TreeNode  t: leftTree) {
                for (TreeNode r : rightTree) {
                    TreeNode node = new TreeNode(i);//defensive copy!!!
                    node.left = t;
                    node.right = r;
                    result.add(node);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<TreeNode> list = generateTrees(3);

    }
}

