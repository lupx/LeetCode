package com.leetcode.number99;

import java.util.ArrayList;
import java.util.List;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);//for the very beginning situation
    TreeNode firstElem = null;//points to the first wrong element.
    TreeNode secElem = null;//points to the second wrong element.

    // the first element after firstElem, when the secElem == null, use this one as the secElem
    TreeNode firstElemSuccessor = null;

    /**
     * 恢复BST, 两个元素位置反了, 要求把这两个元素交换回来
     * O(n)空间算法: 中序遍历, 找到第一个出错的节点,再找出第二个出错的节点, 交换即可
     * 挑战: 用常数空间搞定. 只能用Morris-Traverse遍历(线索二叉树)
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if (root == null) return;

        /**
         * 中序遍历, 在过程中找两个需要交换位置的元素
         * 一个是firstElem
         * 一个是secElem
         */
        traverseInOrder(root);

        /**
         * swap first & sec
         */
        int tmp = firstElem.val;
        if (secElem == null) {
            //firstElem 和 其后继交换
            firstElem.val = firstElemSuccessor.val;
            firstElemSuccessor.val = tmp;
        } else {
            firstElem.val = secElem.val;
            secElem.val = tmp;
        }
    }

    private void traverseInOrder(TreeNode root) {
        if (root == null) return;

        traverseInOrder(root.left);

//        /**
//         * 单纯的解法, secElem有可能永远都更新不到!
//         * 比如, 输入 [0,1], 1是firstElem, 但是secElem永远是null
//         * 后面swap的时候会报nullpointer异常
//         */
//        if (preNode.val >= root.val) {
//            if (firstElem == null) {
//                firstElem = preNode;
//            } else {
//                //此时, 因为firstElem已经找到, 这个分支走到的就是secElem
//                secElem = root;
//            }
//        }
        if (preNode.val >= root.val) {
            if (firstElem == null) {
                firstElem = preNode;
                firstElemSuccessor = root;//record the first successor of the firstElem
            } else {
                if (firstElemSuccessor.val >= preNode.val) {
                    //这种情况其实就是把firstElemSuccessor的两个子节点互相交换即可
                    secElem = preNode;
                } else {
                    secElem = root;
                }
            }
        }
        preNode = root;
        traverseInOrder(root.right);
    }

    public static void main(String[] args) {
    }
}

