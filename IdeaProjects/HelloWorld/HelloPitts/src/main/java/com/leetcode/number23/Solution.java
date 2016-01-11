package com.leetcode.number23;


/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 其实核心思想就是分治, 22合并,然后结果再22合并.
 * 对于2个链表合并的复杂度,应该是O(M+N)
 * 所以这个二分方法的时间复杂度应该是 O((M+N)log(M+N)).
 *
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];

        int n = lists.length;

        /**
         * 把lists分成两份,然后对每一份递归.
         * lists中链表个数>2个时,不用考虑别的,直接divide
         */
        if(n > 2) {
            //divide here
//            ListNode l1 = new ListNode(Integer.MAX_VALUE);
//            ListNode l2 = new ListNode(Integer.MAX_VALUE);
            int half = n / 2;
            ListNode[] l1 = new ListNode[half + 1];
            ListNode[] l2 = new ListNode[n - (half + 1)];
            for (int i = 0; i <= half; i++) {
                l1[i] = lists[i];
            }
            for (int i = 0; i < n - (half + 1); i++) {
                l2[i] = lists[i + (half + 1)];
            }

            ListNode[] list = new ListNode[2];
            list[0] = mergeKLists(l1);//前一半递归,继续分,结果存入list
            list[1] = mergeKLists(l2);//后一半递归,继续分,结果存入list
            return mergeTwoLists(list);//最后合并list
        } else {
            //当lists中只有2个的时候,走这个逻辑,合并这两个list然后返回
            return mergeTwoLists(lists);
        }
    }

    /**
     * 核心逻辑,合并两个列表,并返回结果
     * 这里直接套用了之前的leetcode题目,合并两个有序链表的代码,稍对参数列表做了改动
     * @param lists
     * @return
     */
    public static ListNode mergeTwoLists(ListNode[] lists) {
        ListNode l1 = lists[0];
        ListNode l2 = lists[1];
        if(l1 == null && l2 != null) return l2;
        if(l2 == null && l1 != null) return l1;
        if(l1 == null && l2 == null) return null;

        ListNode needle = new ListNode(0);
        ListNode head = needle;
        int i = 0;
        while(l1 != null && l2 != null) {
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
        if(l1 != null) {
            needle.next = l1;
        }
        if(l2 != null) {
            needle.next = l2;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] n1 = {1,2,6};
        ListNode p = new ListNode(n1[0]);
        ListNode head1 = p;
        for(int i = 1; i < n1.length; i++){
            ListNode node = new ListNode(n1[i]);
            p.next = node;
            p = node;
        }

        int[] n2 = {3,4,9};
        p = new ListNode(n2[0]);
        ListNode head2 = p;
        for(int i = 1; i < n2.length; i++){
            ListNode node = new ListNode(n2[i]);
            p.next = node;
            p = node;
        }

        int[] n3 = {6,8,10,14};
        p = new ListNode(n3[0]);
        ListNode head3 = p;
        for(int i = 1; i < n3.length; i++){
            ListNode node = new ListNode(n3[i]);
            p.next = node;
            p = node;
        }

        ListNode[] lists = new ListNode[3];
        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;

        ListNode list = mergeKLists(lists);

        for(int i = 0; i < 10; i++){
            System.out.println(list.val);
            list = list.next;
        }

    }
}
