package com.leetcode.number232.MyQueueUsingStack;

import java.util.Stack;

/**
 * 要求用栈实现队列
 * 很简单, 两个栈的方案
 * beat 36.05%
 * Created by PeixinLu on 15/12/31.
 */
class MyQueue {
    Stack<Integer> stackIn = new Stack<>();
    Stack<Integer> stackOut = new Stack<>();
    // Push element x to the back of queue.
    // 每次有新元素入队列的时候,
    // stackOut中所有元素弹出来, 并且依序进入StackIn, 然后x入stackIn
    // 然后stackIn所有元素再全部弹出来到StackOut
    public void push(int x) {
        while (!stackOut.isEmpty()) {
            stackIn.push(stackOut.pop());
        }
        stackIn.push(x);
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        stackOut.pop();
    }

    // Get the front element.
    public int peek() {
        return stackOut.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stackOut.isEmpty();
    }
}