package experiments.datastructure.avltree;

public class Utils {

    public static final int MIN_BALANCE_FACTOR = -1;
    public static final int MAX_BALANCE_FACTOR = 1;

    private Utils() {
    }

    public static <T extends Comparable<T>> int getHeight(AVLTree.Node<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    public static <T extends Comparable<T>> void calculateAndAssignHeight(AVLTree.Node<T> node) {
        node.setHeight(Math.max(Utils.getHeight(node.getLeft()), Utils.getHeight(node.getRight())) + 1);
    }

    public static <T extends Comparable<T>> int getBalanceFactor(AVLTree.Node<T> node) {
        return Utils.getHeight(node.getLeft()) - Utils.getHeight(node.getRight());
    }

    public static <T extends Comparable<T>> AVLTree.Node<T> getMinSuccessor(AVLTree.Node<T> node) {
        AVLTree.Node<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
}
