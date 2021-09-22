package experiments.dataStructure.bst_generics_not_working.common;

import java.util.List;

public interface BST<T extends Comparable<T>, N extends BSTNode<T, N>> {

    void insert(T value);

    boolean contains(T value);

    N find(T value);

    void delete(T value);

    void printAsTree();

    List<T> asList();
}
