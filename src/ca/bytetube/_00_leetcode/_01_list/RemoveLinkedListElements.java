package ca.bytetube._00_leetcode._01_list;
//https://leetcode.com/problems/remove-linked-list-elements/description/
public class RemoveLinkedListElements {
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

    public ListNode removeElements(ListNode head, int val) {
//        solution 2
//         dummy Node ->
//         ListNode temp = new ListNode(0, head);
//
//        ListNode current = temp;
//        while (current.next != null){
//            if (current.next.val == val){
//                current.next = current.next.next;
//            }
//            else {
//                current = current.next;
//            }
//        }
//    return temp.next;

        // solution 1 divide
        ListNode newHead = null;
        ListNode newTail = null;
        while (head != null){
            if (head.val != val){
                if (newTail == null) {
                    newHead = head;
                    newTail = head;
                }
                else {
                    newTail.next = head;
                    newTail = head;
                }
            }
            head = head.next;

        }
        if (newTail == null) return null;
        else newTail.next = null;

        return newHead;
    }

    //
}
