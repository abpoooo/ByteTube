package ca.bytetube._00_leetcode._01_list;
//https://leetcode.com/problems/remove-duplicates-from-sorted-list/
public class RemoveDuplicatesFromSortedList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public ListNode deleteDuplicates(ListNode head) {
      ListNode temp = head;
      if (temp == null){
        return head;
      }

      while (temp.next != null){
        if (temp.val == temp.next.val){
          temp.next = temp.next.next;

        }
        else {
          temp = temp.next;
        }
      }
    return head;
  }
}
