package ca.bytetube._00_leetcode._05_bst;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums,0, nums.length - 1);
    }
    private TreeNode traversal(int[] nums, int left, int right){
        if (left > right) return null;
        int middle = left + (right - left) /2;
        TreeNode node = new TreeNode(nums[middle]);
        node.left = traversal(nums,left, middle - 1);
        node.right = traversal(nums,middle + 1, right);
        return node;
    }
}
