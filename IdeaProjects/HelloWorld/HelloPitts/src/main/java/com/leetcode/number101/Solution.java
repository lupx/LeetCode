package com.leetcode.number101;

import java.util.*;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree is symmetric:
 *      1
 *    /  \
 *   2    2
 *  / \  / \
 * 3  4 4  3
 *
 * But the following is not:
 *    1
 *   / \
 *  2   2
 *   \   \
 *   3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * 如果递归和迭代方法都能做出来, 就更好!
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 递归方法:
     * 定义一个helper函数, 两个参数, leftRoot和rightRoot.
     * helper只要比较leftRoot和rightRoot是否是镜像树即可.
     * 1ms
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    /**
     * 比较左右两个子树是否是镜像的
     * @param leftRoot
     * @param rightRoot
     * @return
     */
    private boolean isMirror(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) return true;
        if (leftRoot != null && rightRoot == null) return false;
        if (leftRoot == null) return false;
        if (leftRoot.val != rightRoot.val) return false;

        //左的左子树 和 右的右子树比
        //左的右子树 再和 右的左子树比
        boolean result = isMirror(leftRoot.left, rightRoot.right);
        result &= isMirror(leftRoot.right, rightRoot.left);
        return result;
    }

    /**
     * 其实迭代方式感觉更简单一点:
     * 中序遍历不可行!!!
     * 层序遍历可行:
     * 3ms, 实际比递归慢.
     * @param root
     * @return
     */
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;

        //中序遍历模板, 使用stack
//        Stack<TreeNode> stack = new Stack<>();
//        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
//        List<TreeNode> list = new ArrayList<TreeNode>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode thisNode = stack.peek();
//            if (thisNode.left != null && !map.containsKey(thisNode.left)) {
//                stack.push(thisNode.left);
//            } else {
//                map.put(thisNode, 1);
//                list.add(thisNode);
//                stack.pop();
//                if (thisNode.right != null && !map.containsKey(thisNode.right)) {
//                    stack.push(thisNode.right);
//                }
//            }
//        }

        List<TreeNode> queue = new LinkedList<TreeNode>();//层序遍历的队列
        queue.add(root);
        //在层序遍历的过程中直接检查!
        while (queue.size() > 0) {
            if (queue.size () == 1) {
                TreeNode thisNode = queue.remove(0);
                if (thisNode.left != null && thisNode.right != null) {
                    queue.add(thisNode.left);
                    queue.add(thisNode.right);
                } else if (thisNode.left == null ^ thisNode.right == null) {
                    return false;
                }
            } else {
                int levellen = queue.size();
                int ii = 0;
                int jj = levellen - 1;
                while (ii < jj) {
                    if (queue.get(ii).val != queue.get(jj).val) {
                        return false;
                    } else {
                        //左右子树结构也必须一致
                        if (queue.get(ii).left == null ^ queue.get(jj).right == null)
                            return false;
                        if (queue.get(ii).right == null ^ queue.get(jj).left == null)
                            return false;
                    }
                    ii++;
                    jj--;
                }
                ii = 0;
                while (ii < levellen) {
                    TreeNode thisNode = queue.remove(0);
                    if (thisNode.left != null) queue.add(thisNode.left);
                    if (thisNode.right != null) queue.add(thisNode.right);
                    ii++;
                }
            }
        }//when i comes to the size of the queue, this loop will end.
        return true;
    }

    public static void main(String[] args) {
    }
}

