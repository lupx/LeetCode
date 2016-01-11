package com.leetcode.number237.DeleteGivenNodeInLinkedList;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 *   Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
 *    the linked list should become 1 -> 2 -> 4 after calling your function.
 *
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    /**
     * 这个解法太屌了!!!!膜拜!!!
     * beat 6.03%
     * @param node
     */
    public static void deleteNode(ListNode node) {
        while(node.next != null) {
            node.val = node.next.val;
            if(node.next.next != null) {
                node = node.next;
            }
            else {
                node.next = null;
            }
        }
    }

    public static void main(String[] args) {
        int[] n = {6,6,4,5,7,2,7,5,4,6,6};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }
        deleteNode(head);
        System.out.println("sss");
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}