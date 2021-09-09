package experiments.dataStructure.avl;

import static experiments.dataStructure.avl.Utils.MAX_BALANCE_FACTOR;
import static experiments.dataStructure.avl.Utils.MIN_BALANCE_FACTOR;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root;

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

    public AVLNode<T> find(T value) {
        if (value == null) {
            return null;
        }
        return internalFind(this.root, value);
    }

    public void print() {
        System.out.print("Root: ");
        this.root.print(this.root.height, this.root.height - 1);
    }

    private AVLNode<T> internalInsert(AVLNode<T> node, T value) {
        if (node == null) {
            node = new AVLNode<>(value);
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
    private void internalDelete(AVLNode<T> node, T value) {
        AVLNode<T> parent = null;
        AVLNode<T> current = node;
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
            AVLNode<T> nonNullNode = Objects.requireNonNullElse(current.left, current.right);
            if (current == node) {
                this.root = nonNullNode;
            }
        }
        // Case 3. Two children
        else {
            AVLNode<T> toSwap = Utils.getMinSuccessor(current.getRight());
            internalDelete(current, toSwap.value);
            current.value = toSwap.value;
        }
    }

    private AVLNode<T> internalFind(AVLNode<T> node, T value) {
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

    private AVLNode<T> internalLeftRotate(AVLNode<T> node) {
        AVLNode<T> right = node.right;
        AVLNode<T> rightLeft = right.left;

        right.left = node;
        node.right = rightLeft;

        Utils.calculateAndAssignHeight(node);
        Utils.calculateAndAssignHeight(right);

        return right;
    }

    private AVLNode<T> internalRightRotate(AVLNode<T> node) {
        AVLNode<T> left = node.left;
        AVLNode<T> leftRight = left.right;

        node.left.right = node;
        node.left = leftRight;

        Utils.calculateAndAssignHeight(node);
        Utils.calculateAndAssignHeight(left);

        return left;
    }

    private AVLNode<T> internalLeftRightRotate(AVLNode<T> node) {
        node.left = internalLeftRotate(node.left);
        return internalRightRotate(node);
    }

    private AVLNode<T> internalRightLeftRotate(AVLNode<T> node) {
        node.right = internalRightRotate(node.right);
        return internalLeftRotate(node);
    }

    @Getter
    @Setter
    public static class AVLNode<T extends Comparable<T>> {

        private static final String TREE_PREFIX = "-";

        private T value;
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
