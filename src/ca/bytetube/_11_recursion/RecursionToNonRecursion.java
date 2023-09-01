package ca.bytetube._11_recursion;

import java.util.Stack;

public class RecursionToNonRecursion {
    public static void main(String[] args) {
        log0(4);
        System.out.println("===================");
        log1(4);
    }

    public static void log0(int n){
        if (n < 1) return;
        log0(n-1);
        int v = n + 10;
        System.out.println(v);
    }

    public static void log1(int n){
        Stack<Frame> stack = new Stack();

        //模拟系统入栈
        while (n > 0){
            stack.push(new Frame(n, n+10));
            n--;
        }

        //模拟系统出栈
        while (!stack.isEmpty()){
            Frame frame = stack.pop();
            System.out.println(frame.v);
        }
    }

    private static class Frame{
        int n;
        int v;

        public Frame(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

    public static void log(int n){
        for (int i = 0; i < n; i++) {
            System.out.println(n + 10);
        }
    }


}
