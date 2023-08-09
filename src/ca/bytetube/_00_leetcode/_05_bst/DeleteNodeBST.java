package ca.bytetube._00_leetcode._05_bst;

public class DeleteNodeBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // first recursion to delete the node if on left or right
        if (key < root.val){
            root.left = deleteNode(root.left, key);
            return root;
        }

        else if (key>root.val){
            root.right = deleteNode(root.right, key);
            return root;
        }

        // if it is the root, we have to decide whether root.left == null, then return root.right, otherwise,
        // same for left
        // if both are not null, we have to make sure the BST struc is the same,
        // so we have to find a new node as root which is greater than the largest node in left and smallest node in right

        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                TreeNode min = root.right;
                while (min.left != null) min= min.left;
                root.val = min.val;
                root.right = deleteNode(root.right, min.val);
                return root;
            }
        }

    }
}
