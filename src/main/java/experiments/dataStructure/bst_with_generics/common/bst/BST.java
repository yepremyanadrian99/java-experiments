package experiments.dataStructure.bst_with_generics.common.bst;

import java.util.List;

import experiments.dataStructure.bst_with_generics.common.bstNode.BSTNode;

public interface BST<T extends Comparable<T>, N extends BSTNode<T, N>> {

    void insert(T value);

    boolean contains(T value);

    N find(T value);

    void delete(T value);

    void printAsTree();

    List<T> asList();
}
