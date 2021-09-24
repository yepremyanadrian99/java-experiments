package experiments.dataStructure.bst_with_generics.common.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import experiments.dataStructure.bst_with_generics.common.Utils;
import experiments.dataStructure.bst_with_generics.common.bstNode.BSTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractBST<T extends Comparable<T>, N extends BSTNode<T, N>> implements BST<T, N> {

    protected N root;

    public abstract N nullNodeValue();

    @Override
    public N find(T value) {
        if (value == null) {
            return nullNodeValue();
        }
        return internalFind(this.root, value);
    }

    @Override
    public boolean contains(T value) {
        return find(value) != nullNodeValue();
    }

    /**
     * <b>Careful! Balancing is not done after removing elements.</b>
     */
    @Override
    public void delete(T value) {
        internalDelete(this.root, value);
    }

    @Override
    public List<T> asList() {
        List<T> list = new ArrayList<>();
        internalDFS(this.root, list);
        return list;
    }

    private N internalFind(N node, T value) {
        if (node == nullNodeValue()) {
            return nullNodeValue();
        }
        int comparison = value.compareTo(node.getValue());
        if (comparison > 0) {
            return internalFind(node.getRight(), value);
        } else if (comparison < 0) {
            return internalFind(node.getLeft(), value);
        }
        return node;
    }

    private void internalDelete(N node, T value) {
        N parent = nullNodeValue();
        N current = node;
        while (current != nullNodeValue() && current.getValue().compareTo(value) != 0) {
            parent = current;
            int comparison = value.compareTo(current.getValue());
            if (comparison > 0) {
                current = current.getRight();
            } else if (comparison < 0) {
                current = current.getLeft();
            }
        }
        if (current == nullNodeValue()) {
            return;
        }
        // Case 1. No children
        if (current.getLeft() == nullNodeValue() && current.getRight() == nullNodeValue()) {
            if (current == this.root) {
                this.root = nullNodeValue();
            } else if (parent != nullNodeValue()) {
                if (parent.getLeft() == current) {
                    parent.setLeft(nullNodeValue());
                } else {
                    parent.setRight(nullNodeValue());
                }
            }
        }
        // Case 2. One child
        else if (current.getLeft() == nullNodeValue() || current.getRight() == nullNodeValue()) {
            N nonNullNode = Objects.requireNonNullElse(current.getLeft(), current.getRight());
            if (current == node) {
                this.root = nonNullNode;
            }
        }
        // Case 3. Two children
        else {
            N toSwap = Utils.getMinSuccessor(current.getRight());
            internalDelete(current, toSwap.getValue());
            current.setValue(toSwap.getValue());
        }
    }

    private void internalDFS(N node, List<T> list) {
        if (node == nullNodeValue()) {
            return;
        }
        internalDFS(node.getLeft(), list);
        list.add(node.getValue());
        internalDFS(node.getRight(), list);
    }
}
