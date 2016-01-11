package com.leetcode.number160;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 *    A:      a1 → a2
 *                   ↘
 *                    c1 → c2 → c3
 *                   ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {


    /**
     * 变相的链表找环.
     * 先找到尾, 然后把一个头和尾连接起来, 然后链表找环
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headB;
        while (p.next != null) {
            p = p.next;
        }
        //p为尾节点
        p.next = headB;

        //此时,变为从headA开始找环
        //快慢指针
        ListNode slower = headA;
        ListNode faster = headA;

        while (faster != null && faster.next != null) {//检测是否有环
            faster = faster.next.next;
            slower = slower.next;
            if (faster == slower) break;
        }

        if (faster == null || faster.next == null) {
            p.next = null;
            return null;
        }
        slower = headA;
        while(faster != slower) {
            faster = faster.next;
            slower = slower.next;
        }
        p.next = null;
        return faster;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,5,6};
    }
}

