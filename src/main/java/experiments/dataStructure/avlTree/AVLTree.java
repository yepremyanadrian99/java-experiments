package experiments.dataStructure.avlTree;

import static experiments.dataStructure.avlTree.Utils.MAX_BALANCE_FACTOR;
import static experiments.dataStructure.avlTree.Utils.MIN_BALANCE_FACTOR;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;

    public void insert(T value) {
        this.root = internalInsert(this.root, value);
    }

    /**
     * <b>Careful! Balancing is not done after removing elements.</b>
     */
    public void delete(T value) {
        internalDelete(this.root, value);
    }

    public boolean search(T value) {
        return find(value) != null;
    }

    public Node<T> find(T value) {
        if (value == null) {
            return null;
        }
        return internalFind(this.root, value);
    }

    public void printAsTree() {
        System.out.print("Root: ");
        this.root.print(this.root.height, this.root.height - 1);
    }

    public List<T> asList() {
        return dfs();
    }

    public List<T> dfs() {
        List<T> list = new ArrayList<>();
        internalDFS(this.root, list);
        return list;
    }

    public List<T> bfs() {
        List<T> list = new ArrayList<>();
        int height = this.root.height;
        for (int level = height; level >= 0; --level) {
            internalBFS(this.root, this.root.height, level, list);
        }
        return list;
    }

    private void internalDFS(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        internalDFS(node.getLeft(), list);
        list.add(node.getValue());
        internalDFS(node.getRight(), list);
    }

    private void internalBFS(Node<T> node, int current, int level, List<T> list) {
        if (node == null) {
            return;
        }
        if (current == level) {
            list.add(node.value);
            return;
        }
        internalBFS(node.left, current - 1, level, list);
        internalBFS(node.right, current - 1, level, list);
    }

    private Node<T> internalInsert(Node<T> node, T value) {
        if (node == null) {
            node = new Node<>(value);
        } else {
            int comparison = value.compareTo(node.value);
            if (comparison > 0) {
                node.right = internalInsert(node.right, value);
            } else if (comparison < 0) {
                node.left = internalInsert(node.left, value);
            } else {
                return node;
            }
        }
        Utils.calculateAndAssignHeight(node);
        int balanceFactor = Utils.getBalanceFactor(node);
        // Left-...
        if (balanceFactor > MAX_BALANCE_FACTOR) {
            // Left case
            if (value.compareTo(node.left.value) < 0) {
                node = internalRightRotate(node);
            }
            // Right case
            else {
                node = internalLeftRightRotate(node);
            }
        }
        // Right-...
        else if (balanceFactor < MIN_BALANCE_FACTOR) {
            // Right case
            if (value.compareTo(node.right.value) > 0) {
                node = internalLeftRotate(node);
            }
            // Left case
            else {
                node = internalRightLeftRotate(node);
            }
        }
        return node;
    }

    // TODO: Implement balancing.
    private void internalDelete(Node<T> node, T value) {
        Node<T> parent = null;
        Node<T> current = node;
        while (current != null && current.value.compareTo(value) != 0) {
            parent = current;
            int comparison = value.compareTo(current.value);
            if (comparison > 0) {
                current = current.right;
            } else if (comparison < 0) {
                current = current.left;
            }
        }
        if (current == null) {
            return;
        }
        // Case 1. No children
        if (current.left == null && current.right == null) {
            if (current == this.root) {
                this.root = null;
            } else if (parent != null) {
                if (parent.left == current) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }
        // Case 2. One child
        else if (current.left == null || current.right == null) {
            Node<T> nonNullNode = Objects.requireNonNullElse(current.left, current.right);
            if (current == node) {
                this.root = nonNullNode;
            }
        }
        // Case 3. Two children
        else {
            Node<T> toSwap = Utils.getMinSuccessor(current.getRight());
            internalDelete(current, toSwap.value);
            current.value = toSwap.value;
        }
    }

    private Node<T> internalFind(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        int comparison = value.compareTo(node.value);
        if (comparison > 0) {
            return internalFind(node.right, value);
        } else if (comparison < 0) {
            return internalFind(node.left, value);
        }
        return node;
    }

    private Node<T> internalLeftRotate(Node<T> node) {
        Node<T> right = node.right;
        Node<T> rightLeft = right.left;

        right.left = node;
        node.right = rightLeft;

        Utils.calculateAndAssignHeight(node);
        Utils.calculateAndAssignHeight(right);

        return right;
    }

    private Node<T> internalRightRotate(Node<T> node) {
        Node<T> left = node.left;
        Node<T> leftRight = left.right;

        node.left.right = node;
        node.left = leftRight;

        Utils.calculateAndAssignHeight(node);
        Utils.calculateAndAssignHeight(left);

        return left;
    }

    private Node<T> internalLeftRightRotate(Node<T> node) {
        node.left = internalLeftRotate(node.left);
        return internalRightRotate(node);
    }

    private Node<T> internalRightLeftRotate(Node<T> node) {
        node.right = internalRightRotate(node.right);
        return internalLeftRotate(node);
    }

    @Getter
    @Setter
    public static class Node<T extends Comparable<T>> {

        private static final String TREE_PREFIX = "-";

        private T value;
        private Node<T> left;
        private Node<T> right;
        private int height = 1;

        public Node(T value) {
            this.value = value;
        }

        public void print(int maxHeight, int i) {
            String prefix = "-".repeat(maxHeight - i);
            System.out.println(this.value);
            if (this.left != null) {
                System.out.print(prefix + "Left: ");
                this.left.print(maxHeight, i - 1);
            }
            if (this.right != null) {
                System.out.print(prefix + "Right: ");
                this.right.print(maxHeight, i - 1);
            }
        }
    }
}
