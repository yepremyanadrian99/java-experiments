package experiments.problem.bstInorderSuccessor;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class Utils {

    private Utils() {
    }

    public static <T extends Comparable<T>> BST.Node<T> minGreaterThan(BST.Node<T> node1, BST.Node<T> node2, T value) {
        return Stream.of(node1, node2)
                .filter(Objects::nonNull)
                .filter(node -> node.getValue().compareTo(value) > 0)
                .min(Comparator.comparing(BST.Node::getValue))
                .orElse(null);
    }
}
