package ca.bytetube._00_leetcode._05_bst;
//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0, nums.length - 1);
    }
    private TreeNode traversal(int[] nums, int left, int right){
        if (left > right) return null;
        int m = left + (right - left) /2;
        TreeNode node = new TreeNode(nums[m]);
        node.left = traversal(nums, left, m - 1);
        node.right = traversal(nums, m + 1, right);
        return node;
    }
}
