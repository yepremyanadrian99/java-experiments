package experiments.datastructure.bstgenerics.redBlackTree;

import java.util.Optional;

public class Utils {

    private Utils() {
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

    public static <T extends Comparable<T>> void printer(RedBlackTree.Node<T> node) {
        System.out.println("V: " + node.getValue() + " C: " + node.getColor().name() + " BH: " + node.getBlackHeight());
    }
}
