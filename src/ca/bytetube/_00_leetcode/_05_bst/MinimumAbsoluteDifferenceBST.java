package ca.bytetube._00_leetcode._05_bst;
//https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
public class MinimumAbsoluteDifferenceBST {
    private Integer temp = null;
    private int minDiff = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        traversal(root);
        return minDiff;

    }
    private void traversal(TreeNode node){
        if (node == null) return;

        traversal(node.left);
        if (temp != null) minDiff = Math.min(minDiff, node.val - temp);
        temp = node.val;

        traversal(node.right);
    }
}
