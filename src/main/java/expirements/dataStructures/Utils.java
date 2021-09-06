package expirements.dataStructures;

public class Utils {

    public static final int MIN_BALANCE_FACTOR = -1;
    public static final int MAX_BALANCE_FACTOR = 1;

    private Utils() {
    }

    public static <T extends Comparable<T>> int getHeight(AVLTree.AVLNode<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    public static <T extends Comparable<T>> void calculateAndAssignHeight(AVLTree.AVLNode<T> node) {
        node.setHeight(Math.max(Utils.getHeight(node.getLeft()), Utils.getHeight(node.getRight())) + 1);
    }

    public static <T extends Comparable<T>> int getBalanceFactor(AVLTree.AVLNode<T> node) {
        return Utils.getHeight(node.getLeft()) - Utils.getHeight(node.getRight());
    }
}
