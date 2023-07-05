package ca.bytetube._00_leetcode._03_queue;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/implement-stack-using-queues/description/
class MyStack {

    Queue<Integer> data;
    Queue<Integer> help;
    public MyStack() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(int x) {
        data.offer(x);
    }

    public int pop() {
        while(data.size() > 1) help.offer(data.poll());
        Integer poll = data.poll();

        swap();

        return poll;
    }

    public int top() {
        while(data.size() > 1) help.offer(data.poll());
        Integer poll = data.poll();
        help.offer(poll);
        swap();
        return poll;

    }

    public boolean empty() {
        return data.isEmpty() && help.isEmpty();
    }

    private void swap(){
        Queue<Integer> temp = help;
        help = data;
        data = temp;
    }
}
