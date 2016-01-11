package com.leetcode.number21;

import com.leetcode.number21.ListNode;


/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {
    /**
     * 穿针引线法: 定义一个针头 needle, 两个指针p1,p2, 哪个小, needle.next就指向哪个,然后needle移动
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null&&l2!=null) return l2;
        if(l2==null&&l1!=null) return l1;
        if(l1==null&&l2==null) return null;

        ListNode needle = new ListNode(0);
        ListNode head = needle;
        int i = 0;
        while(l1!=null&&l2!=null) {
            if(l1.val <= l2.val) {
                needle.next = l1;
                l1 = l1.next;
            } else {
                needle.next = l2;
                l2 = l2.next;
            }
            if(i == 0) head = needle.next;
            needle = needle.next;
            i++;
        }
        if(l1!=null) {
            needle.next = l1;
        }
        if(l2!=null) {
            needle.next = l2;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode head = mergeTwoLists(l1,l2);
        while(head!=null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

}
