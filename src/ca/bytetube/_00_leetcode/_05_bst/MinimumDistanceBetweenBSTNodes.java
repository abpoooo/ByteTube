package ca.bytetube._00_leetcode._05_bst;

//https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
public class MinimumDistanceBetweenBSTNodes {
    private Integer temp = null;
    private int minDiff = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        traversal(root);
        return minDiff;
    }

    private void traversal(TreeNode node){
        if (node == null) return;

        traversal(node.left);

        if (temp != null) minDiff = Math.abs(Math.min(minDiff, node.val - temp));
        temp = node.val;

        traversal(node.right);

    }
}
