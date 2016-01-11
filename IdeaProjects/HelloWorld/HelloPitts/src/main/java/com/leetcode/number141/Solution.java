package com.leetcode.number141;

/**
 * LeetCode141: 给一个链表, 要求找是否有环. 要求用常数空间做.
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 如果不要求空间, 每个节点存入hashmap, 遍历一遍看看next会不会存在于map中即可
     * 如果要求常数空间的话, 不能用map. 用快慢指针做:
     * 快指针一次走2步, 慢指针一次走1步
     * 走到两个指针相遇了, 那就肯定有环了!
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode faster = head;
        ListNode slower = head;
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;
            if (faster == slower) return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }
}

