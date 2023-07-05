package ca.bytetube._00_leetcode._02_stack;

import ca.bytetube._04_stack.Stack;

//https://leetcode.com/problems/valid-parentheses/
public class ValidParentheses {
    public boolean isValid(String s) {
//        while (s.contains("{}") || s.contains("[]") || s.contains("()")){
//            s = s.replace("{}", "");
//            s = s.replace("[]", "");
//            s = s.replace("()", "");
//        }
//        return s.isEmpty();


        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (left == '(' && ch != ')') return false;
                if (left == '[' && ch != ']') return false;
                if (left == '{' && ch != '}') return false;
            }
        }
        return stack.isEmpty();
    }
}
