package ca.bytetube._00_leetcode._05_bst;
//https://leetcode.com/problems/search-in-a-binary-search-tree/
public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val < val) return searchBST(root.right, val);
        if (root.val > val) return searchBST(root.left, val);
        if (root.val == val) return root;
        return null;
    }
}
