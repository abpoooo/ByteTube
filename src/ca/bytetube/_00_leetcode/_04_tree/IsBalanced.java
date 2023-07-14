package ca.bytetube._00_leetcode._04_tree;

import ca.bytetube._06_tree.BinaryTree;

public class IsBalanced {

    public static void main(String[] args){
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(4);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(3);
        root.right.right.left = new TreeNode(10);
        root.right.right.right = new TreeNode(12);
        IsBalanced isBalanced = new IsBalanced();
        isBalanced.isBalanced(root);
        System.out.println(isBalanced.isBalanced(root));
        System.out.println(isBalanced.isBalanced1(root));
    }

    /*
    *domain, pojo(bean) --- Person, Order, Product --- table
    *dto(data transfer object)
    */

    private static class ReturnData{
        public boolean isB;
        public int height;

        public ReturnData(boolean isB, int height) {
            this.isB = isB;
            this.height = height;
        }
    }
    public boolean isBalanced(TreeNode root){

        return isB(root).isB;
    }

    private ReturnData isB(TreeNode root){
        if (root == null) return new ReturnData(true,0);

        ReturnData leftData = isB(root.left);
        if (!leftData.isB) return new ReturnData(false, 0);
        ReturnData rightData = isB(root.right);
        if (!rightData.isB) return new ReturnData(false, 0);

        if (Math.abs(leftData.height - rightData.height) > 1) return new ReturnData(false, 0);

        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);

    }


// another way with recursion without dto, choose this easier
    public boolean isBalanced1(TreeNode node){
        if (node == null) return true;

        if (Math.abs(height(node.left) - height(node.right)) > 1) return false;


        return isBalanced1(node.left) && isBalanced1(node.right);
    }

    private int height(TreeNode root){
        if (root == null) return 0;
        return Math.max(height(root.left),height(root.right)) + 1;
    }
}
