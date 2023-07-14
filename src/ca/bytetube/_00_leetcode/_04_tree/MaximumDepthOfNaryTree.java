package ca.bytetube._00_leetcode._04_tree;

//https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
public class MaximumDepthOfNaryTree {
//    int traversal(Node node, int temp){
//        if (node == null) return temp;
//        int maximum = temp;
//        for (int i = 0; i < node.children.size(); i++)
//            maximum = Math.max(temp, traversal(node.children.get(i), temp + 1));
//
//        return maximum;
//    }
//    public int maxDepth(Node root) {
//        if (root == null) return 0;
//        return traversal(root, 1);
//
//    }
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node c: root.children){
            max = Math.max(max, maxDepth(c));
        }
        return max + 1;
    }
}
