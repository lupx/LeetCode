package com.leetcode.number142;

/**
 * LeetCode142: 给一个链表, 如果它有环, 要求判断环的入口在哪里. 如果无环, 返回null
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 快慢指针先找环,第一次faster==slower的时候, slower放回到起点, 然后两个指针同时一步一步的走, 最终两个指针相遇的点, 就是环入口!
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode faster = head;
        ListNode slower = head;
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;
            if (faster == slower) break;
        }

        if (faster == null || faster.next == null) return null;//无环
        slower = head;
        while (faster != slower) {
            faster = faster.next;
            slower = slower.next;
        }
        return faster;
    }

    public static void main(String[] args) {
    }
}

