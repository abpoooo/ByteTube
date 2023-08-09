package ca.bytetube._00_leetcode._05_bst;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        //if both not pass, then LCA is the root
        return root;
    }
}
