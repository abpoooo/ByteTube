package ca.bytetube._07_avl;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E>{
    public AVLTree() {

    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent){
        return new AVLNode<>(element, parent);
    }

    @Override
    public void afterRemove(Node<E> node){//Node = AVLNode
        while ((node = node.parent) != null){//node = root
            if (isBalance(node)){
                calculateHeight(node);
            }else {
                rebalanced(node);
//                break; //有可能祖先节点也失衡 所以要一直向上到root
            }
        }
    }

    @Override
    public void afterAdd(Node<E> node){//Node = AVLNode
        while ((node = node.parent) != null){//node = root
            if (isBalance(node)){
                calculateHeight(node);
            }else {
                rebalanced(node);
                break;
            }
        }
    }

    private void rebalanced2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()){ //L
            if (node.isLeftChild()){    //1. LL -> right rotation
                rotate(grand, node.left, node, node.right,parent, parent.right, grand, grand.right);
            }
            else {    //3. LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right );
            }
        }
        else {  //R
            if (node.isRightChild()){  //2. RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
            else {    //4. RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            }
        }
    }

    private void rebalanced(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()){ //L
            if (node.isLeftChild()){    //1. LL -> right rotation
                rightRotation(grand);
            }
            else {    //3. LR
                leftRotation(parent);
                rightRotation(grand);
            }
        }
        else {  //R
            if (node.isRightChild()){  //2. RR
                leftRotation(grand);
            }
            else {    //4. RL
                rightRotation(parent);
                leftRotation(grand);
            }
        }
    }

    private void leftRotation(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;

        // rotate the line see slide 8 AVL
        grand.right = child;
        parent.left = grand;
        afterRotation(grand, parent, child);
    }



    private void afterRotation(Node<E> grand, Node<E> parent, Node<E> child){
        //renew parent always two side
        //1.  let parent to be the root of this down AVL
        // find new parent
        parent.parent = grand.parent;
        // parent.parent lines back to parent
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        }
        else root= parent;
        // 2. renew child parent node as grand
        if (child != null){
            child.parent = grand;
        }
        //3. renew grand parent node as parent
        grand.parent = parent;

        //4. renew height
        calculateHeight(grand);
        calculateHeight(parent);
    }
    private void rightRotation(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        // rotate the line see slide 8 AVL
        grand.left = child;
        parent.right = grand;
        afterRotation(grand, parent, child);
    }

    /// new way unify rotation operations
    private void rotate(Node<E> r, //用来指定原来树的root， 用r的parent为d的parent
                        Node<E> a,
                        Node<E> b, Node<E> c,
                        Node<E> d, Node<E> e,
                        Node<E> f, Node<E> g){
        //让d成为子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) r.parent.left = d;
        else if (r.isRightChild()) r.parent.right = d;
        else root = d;
        //a-b-c
        b.left = a;
        if (a != null) a.parent = b;
        b.right = c;
        if (c != null) c.parent = b;
        calculateHeight(b);
        //e-f-g
        f.left = e;
        if (e != null) e.parent = f;
        f.right = g;
        if (g != null) g.parent = f;
        calculateHeight(f);
        //b-d-f
        d.left = b;
        b.parent = d;
        d.right = f;
        f.parent = d;
        calculateHeight(d);
    }


    private void calculateHeight(Node<E> node) {
//        int leftHeight = node.left == null ? 0 : ((AVLNode<E>)(node.left)).height;
//        int rightHeight = node.right == null ? 0 : ((AVLNode<E>)(node.right)).height;
//        ((AVLNode<E>) node).height = Math.max(leftHeight, rightHeight) + 1;
        ((AVLNode<E>) node).calculateHeight();
    }

    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }


    public static class AVLNode<E> extends Node<E>{
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor(){
            int leftHeight = left == null ? 0: ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0: ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        private void calculateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0: ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0: ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return this.isLeftChild() ? left : right;
        }
    }
}
