package experiments.dataStructure.redBlackTree;

import java.util.Optional;

public class Utils {

    private Utils() {
    }

    public static <T extends Comparable<T>> int getBlackHeight(RedBlackTree.Node<T> node) {
        return node.getBlackHeight();
    }

    public static <T extends Comparable<T>> void calculateAndAssignBlackHeight(RedBlackTree.Node<T> node) {
        node.setBlackHeight(Math.max(node.getLeft().getBlackHeight(), node.getRight().getBlackHeight()) + 1);
    }

    public static <T extends Comparable<T>> Optional<RedBlackTree.Node<T>> getGrandParent(RedBlackTree.Node<T> node) {
        return Optional.ofNullable(node.getParent())
            .map(RedBlackTree.Node::getParent);
    }

    public static <T extends Comparable<T>> Optional<RedBlackTree.Node<T>> getUncle(RedBlackTree.Node<T> node) {
        return getGrandParent(node).map(grandParent ->
            node.getParent() == grandParent.getLeft() ? grandParent.getRight() : grandParent.getLeft()
        );
    }
}
