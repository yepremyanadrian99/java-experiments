package experiments.dataStructure.bst_generics_not_working.common;

public interface BSTNode<T extends Comparable<T>, N extends BSTNode<T, N>> {

    T getValue();

    void setValue(T value);

    N getLeft();

    void setLeft(N node);

    N getRight();

    void setRight(N node);
}
