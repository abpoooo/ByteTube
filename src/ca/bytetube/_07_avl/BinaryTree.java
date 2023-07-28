package ca.bytetube._07_avl;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> {
    protected int size;
    protected Node<E> root;

    //number
    public int size() {
        return size;
    }

    //empty or not
    public boolean isEmpty() {
        return size == 0;
    }

    //clear all elements
    public void clear(){
        root = null;
        size = 0;
    }



    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }


    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    public void preOrderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrderTraversal0(root, visitor);
    }

    public void preOrderTraversal0(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;
//        System.out.println(node.element + " ");
        visitor.stop = visitor.visit(node.element);
        preOrderTraversal0(node.left, visitor);
        preOrderTraversal0(node.right, visitor);
    }

    // stack having right child right first, have left child push left after
    public void preOrderTraversal(Node<E> node) {
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            System.out.println(pop.element + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
    }

    public void inOrderTraversal(Visitor<E> visitor) {
        inOrderTraversal0(root, visitor);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    public void inOrderTraversal0(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        inOrderTraversal0(node.left, visitor);
//        System.out.println(node.element + " ");
        visitor.stop = visitor.visit(node.element);
        inOrderTraversal0(node.right, visitor);
    }

    //have left push left until end, pop top of stack' elements judge the node have right or not, if it has push right
    public void inOrderTraversal(Node<E> node) {
        if (node != null) {
            Stack<Node<E>> stack = new Stack<>();
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    Node<E> pop = stack.pop();
                    System.out.println(pop.element + " ");
                    node = pop.right;
                }
            }
        }
    }


    public void postOrderTraversal(Visitor<E> visitor) {
        postOrderTraversal0(root, visitor);
    }


    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    public void postOrderTraversal0(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        postOrderTraversal0(node.left, visitor);
        postOrderTraversal0(node.right, visitor);
//        System.out.println(node.element + " ");
        visitor.stop = visitor.visit(node.element);
    }


    public void postOrderTraversal(Node<E> node) {
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Stack<Node<E>> helpStack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            helpStack.push(pop);
            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);
        }
        while (!helpStack.isEmpty()) {
            System.out.println(helpStack.pop().element + " ");
        }
    }

    public void levelOrderTraversal() {
        levelOrderTraversal(root);
    }

    public void levelOrderTraversal(Node<E> node) {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            System.out.println(poll.element + " ");
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }


    }

    public int height() {
        return height(root);
    }

    private int height0(Node<E> node) {
        if (node == null) return 0;
        return Math.max(height0(node.left), height0(node.right)) + 1;
    }

    public int height(Node<E> node) {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        int levelSize = 1;
        while (!queue.isEmpty()) {
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

    public boolean isComplete() {
        return isComplete(root);
    }

    public boolean isComplete(Node<E> head) {
        if (head == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (isLeaf && !node.isLeafNode()) return false;
            //1. if node left != null && node.right != null, left and right to queue
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
            // 2. node.left == null right != null, return false
            else if (node.left == null && node.right != null) return false;

                //3. left!=null && node.right==null, node traversed later should all be leaf nodes
            else if (node.left != null && node.right == null) {
                queue.offer(node.left);
                isLeaf = true;
            }

            //4. left == null && right == null, node traversed later should all be leaf nodes
            else {

                isLeaf = true;
            }
        }
        return true;
    }



    public Node<E> predecessor(Node<E> node){
        if (node == null) return null;


        //1. node.left != null
        Node<E> p = node.left;
        if (p != null){
            while (p.right != null){
                p = p.right;
            }
            return p;
        }

        // 2. node.left == null
        while (node.parent != null && node == node.parent.left) node = node.parent;

        return node.parent;
    }

    public Node<E> successor(Node<E> node){
        if (node == null) return null;

//        1. node.right != null
        Node<E> p = node.right;
        if (p != null){
            while (p.left != null){
                p = p.left;
            }
            return p;
        }

        //2. node.right == null
        while (node.parent != null && node == node.parent.right) node = node.parent;

        return node.parent;
    }


    static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node() {
        }

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }


        protected boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        protected boolean isLeafNode() {
            return this.left == null && this.right == null;
        }

        protected boolean isLeftChild() {
            return parent != null && this == parent.left;
        }
        protected boolean isRightChild() {
            return parent != null && this == parent.right;
        }
        @Override
        public String toString() {
            return "element=" + element;
        }
    }
}
