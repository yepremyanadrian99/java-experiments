package experiments.dataStructure.bst_generics_not_working.avlTree;

import static experiments.dataStructure.bst_generics_not_working.avlTree.Utils.MAX_BALANCE_FACTOR;
import static experiments.dataStructure.bst_generics_not_working.avlTree.Utils.MIN_BALANCE_FACTOR;

import experiments.dataStructure.bst_generics_not_working.common.AbstractBST;
import experiments.dataStructure.bst_generics_not_working.common.AbstractBSTNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AVLTree<T extends Comparable<T>> extends AbstractBST<T, AVLTree.Node<T>> {

    private Node<T> root;

    @Override
    public void insert(T value) {
        this.root = internalInsert(this.root, value);
    }

    @Override
    public void printAsTree() {
        System.out.print("Root: ");
        this.root.print(this.root.height, this.root.height - 1);
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
    public static class Node<T extends Comparable<T>> extends AbstractBSTNode<T, Node<T>> {

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
