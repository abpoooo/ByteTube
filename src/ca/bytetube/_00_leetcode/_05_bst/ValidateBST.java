package ca.bytetube._00_leetcode._05_bst;
//https://leetcode.com/problems/validate-binary-search-tree/description/
public class ValidateBST {
    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (prev != null && prev.val >= root.val) return false;
        prev = root;
        if (!isValidBST(root.right)) return false;
        return true;
    }
}
