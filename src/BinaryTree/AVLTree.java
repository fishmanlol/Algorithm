package BinaryTree;

public class AVLTree<E extends Comparable> extends BST<E> {
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            //if balance
            if (isBalanced(node)) {
                updateHeight(node);
            } else { //not balance, we need recover balance
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode(element, parent);
    }

    private void rebalance(Node<E> g) {
        Node<E> p = ((AVLNode<E>)g).tallerChild();
        Node<E> n = ((AVLNode<E>)p).tallerChild();
        if (p.isLeftChild()) {
            if (n.isLeftChild()) { //LL
                rotateRight(g);
            } else { //LR
                rotateLeft(p);
                rotateRight(g);
            }
        } else {
            if (n.isLeftChild()) { //RL
                rotateRight(p);
                rotateLeft(g);
            } else { //RR
                rotateLeft(g);
            }
        }
    }

    private void rotateLeft(Node<E> node) {

    }

    private  void rotateRight(Node<E> node) {

    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private  void updateHeight(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        avlNode.updateHeight();
    }

    public static class AVLNode<E> extends Node<E> {
        int height = 1;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public  void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (rightHeight > leftHeight) return  right;
            return isLeftChild() ? left : right;
        }
    }
}