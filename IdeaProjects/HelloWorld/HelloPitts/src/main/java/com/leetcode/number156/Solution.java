package com.leetcode.number156;

/**
 * 设计题:
 * 设计一个最小栈, 常数时间弹出最小元素
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * 详细解释:
 * 本实现的特殊点在于,把握住了"栈"这个要素!
 * 如果要求实现的不是栈, 那肯定不能用这个办法来做.
 *
 * 正因为是栈, 所以每次出栈的时候, 只有可能把最近的元素拿出去
 * 也就意味着, 出栈后, 原来栈中的所有属性不会受到刚出栈元素的影响.
 *
 * 这样, 我们每入栈一个x的时候, 可以实时计算出此时最小值, 并且固化到一个变量里.
 * 每次栈顶元素出栈后, 剩余栈的最小值是不受影响的.
 *
 *
 * 8ms, beats 82.51%!!!
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     * 使用自定义链表实现
     * 每次push元素, 新建节点加入链头,
     * 同时,更新min放入新节点的min.
     * 这样,每个节点的min代表了,当自己入栈之后的最小元素.
     * 所以, 每次pop之后, 当前链头的min必定是最小元素
     */
    class Node {
        int x;
        int min;
        Node next;
        Node (int x, int min) {
            this.x = x;
            this.min = min;
        }
    }

    /**
     * 链表头
     */
    Node head = null;

    /**
     * Push element x onto stack.
     * @param x
     */
    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            Node node = new Node(x, Math.min(head.min, x));
            node.next = head;
            head = node;//更新head
        }
    }

    /**
     *  Removes the element on top of the stack.
     */
    public void pop() {
        if (head != null)
            head = head.next;
    }

    public int top() {
        if (head != null)
            return head.x;
        return -1;
    }

    public int getMin() {
        if (head != null)
            return head.min;
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.push(-3);
        System.out.println(s.getMin());
    }

}

