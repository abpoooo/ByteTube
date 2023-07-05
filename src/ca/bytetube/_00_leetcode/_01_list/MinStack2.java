package ca.bytetube._00_leetcode._01_list;
//https://leetcode.com/problems/min-stack/description/

import java.util.Stack;

public class MinStack2 {

    private static class Node{
        int val;
        int min;
        Node next;

        public Node(int val, Node next, int min){
            this.val = val;
            this.next = next;
            this.min = min;
        }

    }
    Node head;

    public MinStack2() {
        head = new Node(0, null, Integer.MAX_VALUE);
    }

    public void push(int val) {
        head = new Node(val, head, Math.min(head.min,val));
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
