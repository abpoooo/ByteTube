package ca.bytetube._00_leetcode._04_tree;//https://leetcode.com/problems/binary-tree-inorder-traversal/


import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {


    public void traversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);

    }

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }
}
