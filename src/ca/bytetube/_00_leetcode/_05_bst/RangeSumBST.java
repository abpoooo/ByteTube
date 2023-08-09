package ca.bytetube._00_leetcode._05_bst;

//https://leetcode.com/problems/range-sum-of-bst/
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= low && root.val <= high) sum+=root.val;
        if (root.val > low) sum+=rangeSumBST(root.left, low, high);
        if (root.val < high) sum+= rangeSumBST(root.right, low, high);
        return sum;
    }
}
