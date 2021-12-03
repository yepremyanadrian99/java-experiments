package experiments.datastructure.bstgenerics.common;

import experiments.datastructure.bstgenerics.common.bstNode.BSTNode;

public class Utils {

    private Utils() {
    }

    public static <T extends Comparable<T>, N extends BSTNode<T, N>> N getMinSuccessor(N node) {
        N current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
}
