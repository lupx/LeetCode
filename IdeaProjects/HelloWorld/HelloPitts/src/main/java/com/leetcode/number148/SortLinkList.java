package com.leetcode.number148;

/**
 * Created by PeixinLu on 15/8/29.
 */
public class SortLinkList {
    public static LinkNode sortList(LinkNode head){
        //driver
        int length = 0;
        LinkNode p = head;
        if(head==null){
            return null;
        }
        while(p!=null){
            length++;
            p=p.next;
        }
        return mergeSort(head,length);
    }

    /**
     * According to 'length', divide the LinkList to two parts: l1 + l2.
     * recursively call this procedure to complete the dividing.
     * At the last step, merge two sorted LinkLists, then return.
     * @param head
     * @param length
     * @return LinkNode, the head node of the sorted List.
     */
    public static LinkNode mergeSort(LinkNode head, int length){
        //divide and merge
        int half = length/2;
        if(length==1){
            return head;
        }
        LinkNode sec;
        LinkNode p = head;
        int count = half-1;
        for(;count>0;count--){
            p = p.next;
        }
        sec = p.next;
        p.next = null;
        //divide
        LinkNode l1 = mergeSort(head,half);
        LinkNode l2 = mergeSort(sec,length-half);

        //merge
        return merge(l1,l2);
    }

    /**
     * Imagine a needle which find the smallest node in the two heads and needle it into the List.
     * When heads move forward, the needle moves forward, repeat the process above.
     * At last, needle all remains.
     * It is DONE!
     * @param l1
     * @param l2
     * @return
     */
    public static LinkNode merge(LinkNode l1, LinkNode l2){
        LinkNode head = new LinkNode(0);
        LinkNode needle = head;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                needle.next = l1;
                l1 = l1.next;
            } else {
                needle.next = l2;
                l2 = l2.next;
            }
            //needle forward
            needle = needle.next;
        }
        if(l1!=null){
            needle.next = l1;
        } else {
            needle.next = l2;
        }
        return head.next;
    }

    /**
     * positive order create a Linked List from a Array name a
     * @param a from which create the LinkedList
     * @return the head node of the LinkList
     */
    public static LinkNode createLinkList(int a[]){
        LinkNode head = new LinkNode(0);
        LinkNode p = new LinkNode(0);
        for(int i=0;i<a.length;i++){
            LinkNode node = new LinkNode(a[i]);
            if(i==0){
                head = node;
                p = node;
            } else {
                p.next = node;
                p = node;
            }
        }
        return head;
    }

    public static void main(String[] args){//main method
        int[] a = {2,1,3,8,4,1,1,5,1,2,0};
        LinkNode head = createLinkList(a);
        LinkNode p = sortList(head);
        while(p!=null){
            System.out.println(p.val);
            p=p.next;
        }
    }
}
