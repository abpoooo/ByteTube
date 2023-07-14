package ca.bytetube._06_tree;


import ca.bytetube._06_tree.BinaryTree.Node;

public class Main {
    public static void main(String[] args){
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
}
