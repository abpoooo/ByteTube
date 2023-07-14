package ca.bytetube._00_leetcode._04_tree;

//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
public class FlattenBinaryTreeToLinkedList {
    TreeNode head = null;
    public void traversal(TreeNode node){

        if (node == null) return;
        traversal(node.right);
        traversal(node.left);

        node.left = null;
        node.right = head;
        head = node;
    }

    public void flatten(TreeNode root){
        if (root == null) return;
        traversal(root);
    }
}
