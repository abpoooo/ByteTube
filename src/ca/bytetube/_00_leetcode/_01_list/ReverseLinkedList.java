package ca.bytetube._00_leetcode._01_list;

//https://leetcode.com/problems/reverse-linked-list/
public class ReverseLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 1. 递归 recursion
//    public ListNode reverseList(ListNode head) {
//        if (head == null || head.next == null){
//            return head;
//        }
//
//        ListNode newHead = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//    }
    //2. change head with tmp and newHead
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

}
