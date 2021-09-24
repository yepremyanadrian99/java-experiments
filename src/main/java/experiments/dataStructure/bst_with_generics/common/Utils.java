package experiments.dataStructure.bst_with_generics.common;

import experiments.dataStructure.bst_with_generics.common.bstNode.BSTNode;

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
