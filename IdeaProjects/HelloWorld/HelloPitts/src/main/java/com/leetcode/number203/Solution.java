package com.leetcode.number203;

import java.util.HashSet;
import java.util.Set;

/**
 * Remove all elements from a linked list of integers that have value val.
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * so easy
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head;
        ListNode pre = dummy;
        while (p != null) {
            if (p.val == val) {
                pre.next = p.next;
            } else {
                pre = pre.next;
            }
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] n = {1,2,6,3,4,5,6};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }

        ListNode h = removeElements(head, 6);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

