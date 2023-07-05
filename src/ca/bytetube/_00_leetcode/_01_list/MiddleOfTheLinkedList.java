package ca.bytetube._00_leetcode._01_list;
//https://leetcode.com/problems/middle-of-the-linked-list/description/
public class MiddleOfTheLinkedList {
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
    public int lenList(ListNode head) {
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode middleNode(ListNode head){
        int len = lenList(head);
        for (int i = 0; i < len/2; i++) {
            head = head.next;
        }
        return head;
    }
}

//   ListNode slow = head;
//        ListNode fast = head;
//        while (fast != null && fast.next!=null){
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
