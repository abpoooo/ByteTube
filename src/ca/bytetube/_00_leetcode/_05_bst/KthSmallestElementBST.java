package ca.bytetube._00_leetcode._05_bst;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// k smallest is in an asc list to be the smallest, so it is an inorder traversal
public class KthSmallestElementBST {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list.get(k-1);
    }
    private void traversal(TreeNode node, List<Integer> list){
        if (node == null) return;
        traversal(node.left, list);
        list.add(node.val);
        traversal(node.right,list);
    }
}
