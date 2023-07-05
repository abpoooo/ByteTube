package ca.bytetube._00_leetcode._01_list;

// 两个单链表相交的一系列问题
//对于单链表来说， 有可能有环， 给定两个单链表 head1， head2 可能相交可能不
//实现一个方法 如果两个相交， 返回相交的第一个节点， 如果不相交返回null 时间复杂度）（m+n） 空间O（1） 即没有新空间

// 拆分为四个问题
// 1. 判断链表是否含环
// 2. 得到两个无环
// 3. 两个有环
// 4. 一个有一个没有 ---> can not stand in space


public class IntersectionLinkedList {
    public static void main(String[] args) {

    }


    public static ListNode getFirstInsertionNode(ListNode head1, ListNode head2) {
        ListNode cycleNode1 =  getCycleNode(head1);
        ListNode cycleNode2 = getCycleNode(head2);
        if (cycleNode1 == null && cycleNode2 == null) {
            return getNoCycleIntersectionNode(head1, head2);
        }
        if (cycleNode1 != null && cycleNode2 != null) {
            getTwoCycleIntersectionNode(head1, head2, cycleNode2, cycleNode1);
        }
        return null;
    }

    public static ListNode getTwoCycleIntersectionNode(ListNode head1, ListNode cycleNode1, ListNode cycleNode2, ListNode head2) {
        ListNode cur1 = null;
        ListNode cur2 = null;
        if (cycleNode1 == cycleNode2) { // II
            cur1 = head1;
            cur2 = head2;
            int difference = 0;
            while (cur1.next != cycleNode1) {
                cur1 = cur1.next;
                difference++;
            }
            while (cur2.next != cycleNode2) {
                difference--;
                cur2 = cur2.next;
            }

            cur1 = difference > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            difference = Math.abs(difference);

            //let longer linkedList run difference length then let 2 pivot run together,
            // the position/ node they meet is the first cross point
            while (difference != 0) {
                difference--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//I or III
            cur1 = cycleNode1.next;
            while (cur1 != cycleNode1) {
                if (cur1 == cycleNode2) {//III
                    return cycleNode1;
                }
                cur1 = cur1.next;
            }
        }
        return null;//I
    }

    public static ListNode getNoCycleIntersectionNode(ListNode head1, ListNode head2) {
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        int difference = 0;
        while (cur1.next != null) {
            cur1 = cur1.next;
            difference++;
        }
        while (cur2.next != null) {
            difference--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) return null;
        cur1 = difference > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        difference = Math.abs(difference);

        //let longer linkedList run difference length then let 2 pivot run together,
        // the position/ node they meet is the first cross point
        while (difference != 0) {
            difference--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static ListNode getCycleNode(ListNode head) {
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;

        }
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
