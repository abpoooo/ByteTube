package ca.bytetube._00_leetcode._04_tree;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/n-ary-tree-postorder-traversal/
public class NaryTreePostorderTraversal {
    List<Integer> res = new ArrayList<>();
    public List<Integer> postorder(Node root) {

        if (root == null) return res;

        for (Node i : root.children){
            postorder(i);
        }
        res.add(root.val);
        return res;
    }
}
