package ca.bytetube._00_leetcode._04_tree;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/n-ary-tree-preorder-traversal/
public class NaryTreePreorderTraversal {
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {

        if (root == null) return res;
        res.add(root.val);
        for (Node i : root.children){
            preorder(i);
        }
        return res;
    }
}
