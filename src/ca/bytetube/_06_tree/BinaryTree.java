package ca.bytetube._06_tree;


import ca.bytetube._00_leetcode._04_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> {
    private Node<E> root;


    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    public void preOrderTraversal0(Node<E> node){
        if (node == null) return;
        System.out.println(node.element + " ");
        preOrderTraversal0(node.left);
        preOrderTraversal0(node.right);
    }

    // stack having right child right first, have left child push left after
    public void preOrderTraversal(Node<E> node){
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()){
            Node<E> pop = stack.pop();
            System.out.println(pop.element + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
    }

    public void inOrderTraversal0(Node<E> node){
        if (node == null) return;
        inOrderTraversal0(node.left);
        System.out.println(node.element + " ");
        inOrderTraversal0(node.right);
    }

    //have left push left until end, pop top of stack' elements judge the node have right or not, if it has push right
    public void inOrderTraversal(Node<E> node){
        if (node != null) {
            Stack<Node<E>> stack = new Stack<>();
            while (!stack.isEmpty() || node != null){
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                }else {
                    Node<E> pop = stack.pop();
                    System.out.println(pop.element + " ");
                    node = pop.right;
                }
            }
        }
    }

    public void postOrderTraversal0(Node<E> node){
        if (node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.element + " ");

    }

    public void postOrderTraversal(){
        postOrderTraversal(root);
    }

    public void postOrderTraversal(Node<E> node){
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Stack<Node<E>> helpStack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()){
            Node<E> pop = stack.pop();
            helpStack.push(pop);
            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);
        }
        while (!helpStack.isEmpty()){
            System.out.println(helpStack.pop().element + " ");
        }
    }

    public void levelOrderTraversal(){
        levelOrderTraversal(root);
    }
    public void levelOrderTraversal(Node<E> node){
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            Node<E> poll = queue.poll();
            System.out.println(poll.element + " ");
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }


    }

    public int height(){
        return height(root);
    }
    private int height0(Node<E> node){
        if (node == null) return 0;
        return Math.max(height0(node.left),height0(node.right)) + 1;
    }

    public int height(Node<E> node){
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        int levelSize = 1;
        while (!queue.isEmpty()){
            Node<E> poll = queue.poll();
            levelSize--;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);

            if (levelSize == 0) {//意味着当前层遍历结束， 即将遍历下一层
                height++;
                levelSize = queue.size();
            }
        }

        return 0;
    }

    public boolean isComplete(){
        return isComplete(root);
    }
    public boolean isComplete(Node<E> head) {
        if (head == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        boolean isLeaf = false;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (isLeaf && !node.isLeafNode() ) return false;
            //1. if node left != null && node.right != null, left and right to queue
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
            // 2. node.left == null right != null, return false
            else if (node.left == null && node.right != null) return false;

                //3. left!=null && node.right==null, node traversed later should all be leaf nodes
            else if (node.left != null && node.right == null){
                queue.offer(node.left);
                isLeaf = true;
            }

            //4. left == null && right == null, node traversed later should all be leaf nodes
            else{

                isLeaf = true;
            }
        }
        return true;
    }




    static class Node<E>{
        E element;
        Node<E> left;
        Node right;

        public Node(){}

        public Node(E element){
            this.element = element;
        }


        private boolean hasTwoChildren(){
            return this.left != null && this.right != null;
        }
        private boolean isLeafNode(){
            return this.left == null && this.right == null;
        }
    }
}
