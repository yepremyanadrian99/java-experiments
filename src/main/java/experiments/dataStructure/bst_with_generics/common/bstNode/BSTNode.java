package experiments.dataStructure.bst_with_generics.common.bstNode;

public interface BSTNode<T extends Comparable<T>, N extends BSTNode<T, N>> {

    T getValue();

    void setValue(T value);

    N getLeft();

    void setLeft(N node);

    N getRight();

    void setRight(N node);
}
