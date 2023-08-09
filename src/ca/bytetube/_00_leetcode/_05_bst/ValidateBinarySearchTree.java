package ca.bytetube._00_leetcode._05_bst;

//https://leetcode.com/problems/validate-binary-search-tree/description/
public class ValidateBinarySearchTree {

    public boolean isValidBST0(TreeNode root) {
        if (root == null) return true;

        isValidBST0(root.left);
        if (root != null){
            if ((root.left != null && root.val <= root.left.val) || (root.right != null && root.val >= root.right.val)) return false;
        }


        isValidBST0(root.right);

        return true;
        // wrong answer for 62/82 test cases
    }

    // solution for inorder traversal provided a pre TreeNode
    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST(root.left)) return false;

        if (prev != null && prev.val >= root.val) return false;

        prev = root;

        if (!isValidBST(root.right)) return false;

        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, null, null);
    }

    //best avoid MAX_VALUE and MIN_VALUE
    private boolean isValidBST2(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min.val) {
            return false;
        }

        if (max != null && root.val >= max.val) {
            return false;
        }
        return isValidBST2(root.left, min, root)
                && isValidBST2(root.right, root, max);
    }

}
