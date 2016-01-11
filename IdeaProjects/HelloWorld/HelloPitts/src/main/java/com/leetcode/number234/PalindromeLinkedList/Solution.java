package com.leetcode.number234.PalindromeLinkedList;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * 确定一个单链表是不是回文链表
 *
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    /**
     * 简单的做法, O(n)空间的做法: 新建一个链表, 其把原链表反转, 然后和原链表挨个比对一下, 全部节点一样, 就说明是个回文串
     * 这里实现O(1)space的算法:
     * 原链表快慢指针找到中点, 然后从中点开始,把后半部分翻转, 然后两个指针一个从head开始,一个从中点开始往后挨个比对!
     *
     * beat 43.10%
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        if (head.next == null) return true;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode f = head;//faster
        ListNode s = dummy;//slower
        int n = 0;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            n++;
        }
        boolean isOdd = f != null;
        //此时s.next即为mid
        //把s后半部分翻转, 然后s.next指向翻转后的头
        //有个细节, 奇数个节点的时候, 从mid.next开始做比较. 偶数个节点的时候, 从mid开始做比较
        //上面n为偶数, 就是偶数个节点, n为奇数,就是奇数个节点.
        dummy.next = s.next;
        ListNode tmp;
        ListNode p1 = s.next.next;
        ListNode p2 = s.next;
        ListNode tail = isOdd ? s.next.next : s.next; // 两种不同情况下的尾巴
        //下面是翻转
        while (p1 != null) {
            tmp = p1.next;
            p1.next = p2;
            p2 = p1;
            p1 = tmp;
        }
        //翻转结束后, p2所指为新头, 题目没要求链表不能动, 可以不换链, 但是tail必须指向null
//        if (!isOdd) s.next = p2;
//        else s.next.next = p2;
        tail.next = null;
        //下面, 根据isOdd从mid或者mid.next开始做比较
        p1 = head;//此时, p2是后半部头
        while (p1 != s.next) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
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
        System.out.println(isPalindrome(head));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}