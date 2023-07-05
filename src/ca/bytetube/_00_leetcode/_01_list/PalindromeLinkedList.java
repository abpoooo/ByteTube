package ca.bytetube._00_leetcode._01_list;
//https://leetcode.com/problems/palindrome-linked-list/
public class PalindromeLinkedList {

    //      Definition for singly-linked list.
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

    /**
     * /
     * 思路 1.借助快慢指针找到list终点
     * 2.后半段反转
     * 3.设置两个指针两个指针（头尾）分别让两个纸扎嗯向中间靠近， 进行回文判断
     * 4.最后恢复方向（再次反转后半部分）
     *
     */

    public boolean isPalindrome(ListNode head) {
        // 1.借助快慢指针找到list终点
        ListNode middleNode = getMiddle(head);
        //2.后半段反转
        ListNode rightHead = reverseList(middleNode.next);
        //3.设置两个指针两个指针（头尾）分别让两个纸扎嗯向中间靠近， 进行回文判断
        ListNode oldRightHead = rightHead;
        ListNode leftHead = head;
        boolean isPalindrome = true;
        while(rightHead != null){
            if (leftHead.val != rightHead.val){
                isPalindrome = false;
                break;
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }
        //4.最后恢复方向（再次反转后半部分）
        reverseList(oldRightHead);
        return isPalindrome;
    }
    public ListNode getMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

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
