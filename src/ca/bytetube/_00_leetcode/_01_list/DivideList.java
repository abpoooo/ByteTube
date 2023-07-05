package ca.bytetube._00_leetcode._01_list;

public class DivideList {
    public static void main(String[] args){
        ListNode head = new ListNode(16, null);
        head.next = new ListNode(2, null);
        head.next.next = new ListNode(14, null);
        head.next.next.next = new ListNode(8, null);
        head.next.next.next.next = new ListNode(7, null);
        head.next.next.next.next.next = new ListNode(3, null);
        head.next.next.next.next.next.next = new ListNode(15, null);
        head.next.next.next.next.next.next.next = new ListNode(3, null);
        ListNode divideList = divideList(head, 8);
        System.out.println();
    }



    public static ListNode divideList(ListNode head, int pivot) {
        ListNode lessHead = null;
        ListNode lessTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode moreHead = null;
        ListNode moreTail = null;

        ListNode next = null;
        while (head != null){
            next = head.next;
            if (head.val < pivot) {
                if (lessHead == null) {
                    lessHead = head;
                    lessTail = head;
                }
                else {
                    lessTail.next = head;
                    lessTail = head;
                }

            }
            else if(head.val == pivot){
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                }
                else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }
            else {
                if (moreHead == null) {
                    moreHead = head;
                    moreTail = head;
                }
                else {
                    moreTail.next = head;
                    moreTail = head;
                }

            }

            head = next;
        }

        // less --> equal
        if (lessTail != null) {
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }

        // equal --> more
        if (equalTail != null) {
            equalTail.next = moreHead;
            //moreTail = moreTail == null ? equalTail : moreTail;
        }

        return lessHead != null ? lessHead : equalHead != null ? equalHead : moreHead;
    }
}
