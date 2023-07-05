package ca.bytetube._00_leetcode._02_stack;
//https://leetcode.com/problems/min-stack/

import java.util.Stack;

public class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        dataStack.push(val);
        if (minStack.isEmpty()) minStack.push(val);
        else if (val <= minStack.peek()) minStack.push(val);
    }

    public void pop() {
        int pop = 0;

        if (!dataStack.isEmpty()) pop = dataStack.pop();

        if (pop == minStack.peek()) minStack.pop();

    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) throw new RuntimeException("Stack is empty");

        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
