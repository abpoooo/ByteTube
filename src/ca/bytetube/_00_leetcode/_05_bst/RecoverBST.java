package ca.bytetube._00_leetcode._05_bst;

//https://leetcode.com/problems/recover-binary-search-tree/
public class RecoverBST {
    //set up three node as prev first and second to be change in inorder traversal
    // we use inorder to get a sorted tree and if the root.val < prev.val means we find a wrong position
    // then we use first and second and prev to change the position to correct one and recursion to find next
    TreeNode prev = null, first = null, second = null;
     public void traversal(TreeNode root){
         if (root == null) return;
         traversal(root.left);
         if (prev!=null && root.val < prev.val){
             if (first == null) first = prev;
             second = root;
         }
         prev = root;
         traversal(root.right);
    }

    public void recoverTree(TreeNode root) {
        if (root == null) return;
        traversal(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
