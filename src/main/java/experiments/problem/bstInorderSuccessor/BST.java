package experiments.problem.bstInorderSuccessor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BST<T extends Comparable<T>> {

    private Node<T> root;

    public void insert(T value) {
        root = internalInsert(null, root, value);
    }

    public Node<T> find(T value) {
        if (value == null) {
            return null;
        }
        return internalFind(root, value);
    }

    // Average - O(log(N))
    // Worst   - O(N)
    public Node<T> inorderSuccessor(T value) {
        Node<T> valueNode = find(value);
        if (valueNode == null) {
            throw new RuntimeException("Node not found");
        }
        Node<T> downNode = internalInorderSuccessor(value, valueNode.getRight(), null, true);
        Node<T> upNode = internalInorderSuccessor(value, valueNode.getParent(), null, false);
        return Utils.minGreaterThan(downNode, upNode, value);
    }

    private Node<T> internalInorderSuccessor(T value, Node<T> node, Node<T> currentMin, boolean isDown) {
        if (node == null) {
            return currentMin;
        }
        if (isDown) {
            return internalInorderSuccessor(value, node.getLeft(), Utils.minGreaterThan(node, currentMin, value), true);
        }
        return internalInorderSuccessor(value, node.getParent(), Utils.minGreaterThan(node, currentMin, value), false);
    }

    private Node<T> internalInsert(Node<T> parent, Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value, parent);
        }
        int comparison = value.compareTo(node.getValue());
        if (comparison == 0) {
            return node;
        }
        if (comparison < 0) {
            node.setLeft(internalInsert(node, node.getLeft(), value));
        } else {
            node.setRight(internalInsert(node, node.getRight(), value));
        }
        return node;
    }

    private Node<T> internalFind(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        int comparison = value.compareTo(node.getValue());
        if (comparison == 0) {
            return node;
        }
        if (comparison < 0) {
            return internalFind(node.getLeft(), value);
        }
        return internalFind(node.getRight(), value);
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    public static final class Node<T> {

        private final T value;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        public Node(T value, Node<T> parent) {
            this(value);
            this.parent = parent;
        }
    }
}
