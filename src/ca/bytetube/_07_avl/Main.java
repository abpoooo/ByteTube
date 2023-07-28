package ca.bytetube._07_avl;


import ca.bytetube._07_avl.BinaryTree.Node;

import java.util.Random;

public class Main {

    public static void test4() {
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < 10; i++) {
            avl.add(i);
        }


    }

//    public static void test3() {
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        Random random = new Random();
////        for (int i = 10; i < 20; i++) {
////            bst.add(i);
////        }
//
//        bst.add(8);
//        bst.add(3);
//        bst.add(10);
//        bst.add(1);
//        bst.add(6);
//        bst.add(14);
//        bst.add(4);
//        bst.add(7);
//        bst.add(13);
//
//
//        System.out.println(bst.contains(12));
//        System.out.println(bst.successor(bst.node(8)));
//        System.out.println(bst.predecessor(bst.node(8)));
//    }



//    public static void test1() {
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        Random random = new Random();
////        for (int i = 10; i < 20; i++) {
////            bst.add(i);
////        }
//
//        bst.add(9);
//        bst.add(11);
//        bst.add(10);
//        bst.add(12);
////        bst.add(8);
////        bst.add(3);
////        bst.add(10);
////        bst.add(1);
////        bst.add(6);
////        bst.add(14);
////        bst.add(4);
////        bst.add(7);
////        bst.add(13);
//
//
//        System.out.println(bst.contains(12));
//        System.out.println(bst.successor(bst.node(8)));
//        System.out.println(bst.predecessor(bst.node(8)));
//
//    }

    public static void test0() {

        Node<Integer> root = new BinaryTree.Node<>(7);
        root.left = new Node(4);
        root.right = new Node(9);
        root.left.left = new Node(2);
        root.left.right = new Node(5);
        root.right.left = new Node(8);
        root.right.right = new Node(11);
        root.left.left.left = new Node(1);
        root.left.left.right = new Node(3);
        root.right.right.left = new Node(10);
        root.right.right.right = new Node(12);


        BinaryTree<Integer> binaryTree = new BinaryTree<>();
//        binaryTree.inOrderTraversal(root);
//        binaryTree.preOrderTraversal(root);
        binaryTree.postOrderTraversal(root);
//        binaryTree.levelOrderTraversal(root);

        System.out.println();

    }

    public static void main(String[] args) {

        test4();


//    public static void test1(){
//        BinarySearchTree<anonymous.Person> bst = new BinarySearchTree<>(new PersonComparator());
//    }


    }
}