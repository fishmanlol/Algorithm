import BinaryTree.*;
import Printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<Integer>();
        avl.add(4);
        avl.add(2);
        avl.add(7);
        avl.add(1);
        avl.add(3);
        avl.add(6);
        avl.add(9);

        BinaryTrees.print(avl);
    }
}
