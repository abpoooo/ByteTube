package ca.bytetube._00_leetcode._04_tree;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/check-completeness-of-a-binary-tree/
public class CheckCompletenessOfBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeaf = false;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (isLeaf && !isLeafNode(node) ) return false;
            //1. if node left != null && node.right != null, left and right to queue
            if (hasTwoChildren(node)) {
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



    private boolean hasTwoChildren(TreeNode node){
        return node.left != null && node.right != null;
    }
    private boolean isLeafNode(TreeNode node){
        return node.left == null && node.right == null;
    }
}
