package expirements.dataStructures;

import static expirements.dataStructures.Utils.MAX_BALANCE_FACTOR;
import static expirements.dataStructures.Utils.MIN_BALANCE_FACTOR;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root;

    public void insert(T value) {
        this.root = internalInsert(this.root, value);
    }

    public boolean search(T value) {
        return find(value) != null;
    }

    public AVLNode<T> find(T value) {
        if (value == null) {
            return null;
        }
        return internalFind(this.root, value);
    }

    public AVLNode<T> leftRotate(AVLNode<T> node) {
        AVLNode<T> right = node.right;
        AVLNode<T> rightLeft = right.left;

        right.left = node;
        node.right = rightLeft;

        Utils.calculateAndAssignHeight(node);
        Utils.calculateAndAssignHeight(right);

        return right;
    }

    public AVLNode<T> rightRotate(AVLNode<T> node) {
        AVLNode<T> left = node.left;
        AVLNode<T> leftRight = left.right;

        node.left.right = node;
        node.left = leftRight;

        Utils.calculateAndAssignHeight(node);
        Utils.calculateAndAssignHeight(left);

        return left;
    }

    public void print() {
        System.out.print("Root: ");
        this.root.print(this.root.height, this.root.height - 1);
    }

    private AVLNode<T> internalInsert(AVLNode<T> node, T value) {
        if (node == null) {
            node = new AVLNode<>(value);
        } else {
            int comparison = node.value.compareTo(value);
            if (comparison > 0) {
                node.left = internalInsert(node.left, value);
            } else if (comparison < 0) {
                node.right = internalInsert(node.right, value);
            }
        }
        Utils.calculateAndAssignHeight(node);
        int balanceFactor = Utils.getBalanceFactor(node);
        // Left-...
        if (balanceFactor > MAX_BALANCE_FACTOR) {
            // Left case
            if (value.compareTo(node.left.value) < 0) {
                node = rightRotate(node);
            }
            // Right case
            if (value.compareTo(node.left.value) > 0) {
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        }
        // Right-...
        else if (balanceFactor < MIN_BALANCE_FACTOR) {
            // Right case
            if (value.compareTo(node.right.value) > 0) {
                node = leftRotate(node);
            }
            // Left case
            if (value.compareTo(node.right.value) < 0) {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }
        return node;
    }

    private AVLNode<T> internalFind(AVLNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        int comparison = node.value.compareTo(value);
        if (comparison == 0) {
            return node;
        } else if (comparison > 0) {
            return internalFind(node.left, value);
        }
        return internalFind(node.right, value);
    }

    @Getter
    @Setter
    public static class AVLNode<T extends Comparable<T>> {

        private static final String TREE_PREFIX = "-";

        private final T value;
        private AVLNode<T> left;
        private AVLNode<T> right;
        private int height = 1;

        public AVLNode(T value) {
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