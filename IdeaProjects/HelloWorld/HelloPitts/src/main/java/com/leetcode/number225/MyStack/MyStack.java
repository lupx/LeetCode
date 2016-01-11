package com.leetcode.number225.MyStack;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目要求用多个队列, 实现栈的功能, 包含方法如下面所示.
 * 用队列实现stack, 要点在于实现后进先出的规则!
 * 重点在于, 每次元素入队列的时候, 把队中已有元素挨个出队, 然后重新插入到队列尾部
 * beat 59.88%
 * Created by PeixinLu on 15/12/30.
 */
class MyStack {
    /**
     * 用linkedlist实现的queue
     */
    Queue<Integer> queue = new LinkedList<>();


    // Push element x onto stack.
    // x入队列, 同时把队列中已有元素挨个出队列, 重新在x后面入队列
    public void push(int x) {
        int i = 0;
        int size = queue.size();
        queue.offer(x);
        while (i < size) {
            int tmp = queue.poll();
            queue.offer(tmp);
            i++;
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        queue.poll();
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main (String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.empty());
    }
}