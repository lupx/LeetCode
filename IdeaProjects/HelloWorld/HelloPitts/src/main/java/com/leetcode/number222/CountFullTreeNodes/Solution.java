package com.leetcode.number222.CountFullTreeNodes;

/**
 * 给一个满二叉树, 要求数出它其中的节点个数
 * 注意, 满二叉树除最底层外, 上面各层全部都是满节点, 最后一层节点数从左到右排列.
 * 层序遍历肯定没问题, 但是明显速度太慢, 如果树非常大, 可能会TLE
 *
 *
 * Created by Peixin Lu on 15/12/29.
 */
public class Solution {

    /**
     * 这个题用二分查找来做!
     * beat 97.85%
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return 1;

        int height = 0;
        int nodesSum = 0;
        TreeNode curr = root;
        //nodesSum先计算的除去最后一层外所有节点的个数, 很显然是 2^h - 1, 这里的h比树高度小1
        while(curr.left!=null) {
            nodesSum += (1<<height);
            height++;
            curr = curr.left;
        }
        return nodesSum + countLastLevel(root, height);
    }

    /**
     * 核心在这个算法!
     * 我们从一开始约定一个midNode(这个选的很有艺术性！). 然后用currentheight和树整体的height作比较，一层层往下找midNode。直到当前height == 刚才算出来的height。
     *    这个时候：
     *    midNode如果为空, 那么说明最后一层的叶子节点一定都在左半层，所以从root的左子树开始, height-1继续递归
     *    midNode如果不为空, 那么说明前半层全满, 那么个数为 1 << (height - 1), 然后再从右半部分递归, height - 1
     *
     * 归根结底，每一次递归， 把原来的节点范围缩小约一半。所以，这个方法时间复杂度应为O(logH)
     *
     * 为什么midNode选的很有艺术性?
     *  midNode实际上是root节点左子树的最右边节点. 所以如果这个节点不为空, 那么左半层叶子肯定全满(root左子树全满), 那么左半层叶子精确可数!
     * @param root
     * @param height
     * @return
     */
    private int countLastLevel(TreeNode root, int height) {
        if(height==1)
            if (root.right!=null) return 2;
            else if (root.left!=null) return 1;
            else return 0;
        TreeNode midNode = root.left;
        int currHeight = 1;
        while(currHeight<height) {
            currHeight++;
            midNode = midNode.right;
        }
        if (midNode==null) return countLastLevel(root.left, height-1);
        else return (1<<(height-1)) + countLastLevel(root.right, height-1);
    }

    public static void main(String[] args) {
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}
