package experiments.dataStructure.bst_generics_not_working.redBlackTree;

import java.util.function.Consumer;

import experiments.dataStructure.bst_generics_not_working.common.AbstractBST;
import experiments.dataStructure.bst_generics_not_working.common.AbstractBSTNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RedBlackTree<T extends Comparable<T>> extends AbstractBST<T, RedBlackTree.Node<T>> {

    private static final String CANT_BE_NULL_ERROR = "can't be null";
    private static final String GRAND_PARENT_IS_NULL_ERROR = "Grand parent " + CANT_BE_NULL_ERROR;
    private static final String UNCLE_IS_NULL_ERROR = "Grand parent " + CANT_BE_NULL_ERROR;

    public static final Node NIL = new Node(Node.Color.BLACK);

    public RedBlackTree() {
        root = NIL;
    }

    @Override
    public void insert(T value) {
        setRoot(internalInsert(null, getRoot(), value));
    }

    @Override
    public void printAsTree() {
        getRoot().print(Utils::printer, getRoot().blackHeight, getRoot().blackHeight - 1);
    }

    private Node<T> internalInsert(Node<T> parent, Node<T> node, T value) {
        if (node.isNil()) {
            node = new Node<>(value);
            node.setParent(parent);
        } else {
            int comparison = value.compareTo(node.getValue());
            if (comparison > 0) {
                internalInsert(node, node.getRight(), value);
            } else if (comparison < 0) {
                internalInsert(node, node.getLeft(), value);
            } else {
                return node;
            }
        }
        Utils.calculateAndAssignBlackHeight(node);
        if (getRoot().isNil() || getRoot() == node || parent == null) {
            node.setBlack();
            return node;
        }
        if (node.isBlack() || node.getParent().isBlack()) {
            return node;
        }
        Node<T> grandParent = Utils.getGrandParent(node)
            .orElseThrow(() -> new RuntimeException(GRAND_PARENT_IS_NULL_ERROR));
        Node<T> uncle = Utils.getUncle(node)
            .orElseThrow(() -> new RuntimeException(UNCLE_IS_NULL_ERROR));
        if (uncle.isRed()) {
            node.getParent().setBlack();
            uncle.setBlack();
            grandParent.setRed();
            return node;
        }
        // Left-...
        if (node.getParent() == grandParent.getLeft()) {
            // Left case
            if (node == parent.getLeft()) {
                grandParent.getParent().setLeft(rightRotate(grandParent));
                recolor(parent);
            }
            // Right case
            else {
                grandParent.getParent().setLeft(leftRightRotate(grandParent));
                recolor(node);
            }
        }
        // Right-...
        else {
            // Right case
            if (node == parent.getRight()) {
                grandParent.getParent().setRight(leftRotate(grandParent));
                recolor(parent);
            }
            // Left case
            else {
                grandParent.getParent().setRight(rightLeftRotate(grandParent));
                recolor(node);
            }
        }
        return node;
    }

    private void recolor(Node<T> newParent) {
        newParent.setBlack();
        newParent.getLeft().setRed();
        newParent.getRight().setRed();
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> right = node.getRight();
        Node<T> rightLeft = right.getLeft();

        right.setLeft(node);
        node.setRight(rightLeft);

        Utils.calculateAndAssignBlackHeight(node);
        Utils.calculateAndAssignBlackHeight(right);

        return right;
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> left = node.getLeft();
        Node<T> leftRight = left.getRight();

        left.setRight(node);
        node.setLeft(leftRight);

        Utils.calculateAndAssignBlackHeight(node);
        Utils.calculateAndAssignBlackHeight(left);

        return left;
    }

    private Node<T> leftRightRotate(Node<T> node) {
        node.setLeft(leftRotate(node.getLeft()));
        return rightRotate(node);
    }

    private Node<T> rightLeftRotate(Node<T> node) {
        node.setRight(rightRotate(node.getRight()));
        return leftRotate(node);
    }

    @Getter
    @Setter
    @ToString(exclude = {"parent"})
    public static class Node<T extends Comparable<T>> extends AbstractBSTNode<T, Node<T>> {

        private Node<T> parent;
        private int blackHeight = 0;
        private Color color;

        public Node(Color color) {
            this.color = color;
        }

        public Node(T value) {
            this(Color.RED);
            this.value = value;
            super.left = NIL;
            super.right = NIL;
        }

        @Override
        public void setLeft(Node<T> left) {
            super.setLeft(left);
            this.left.parent = this;
        }

        @Override
        public void setRight(Node<T> right) {
            super.setLeft(left);
            this.right.parent = this;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
            if (parent != null) {
                int comparison = this.value.compareTo(parent.value);
                if (comparison > 0) {
                    parent.right = this;
                } else {
                    parent.left = this;
                }
            }
        }

        public boolean isNil() {
            return this == NIL;
        }

        public boolean isRed() {
            return this.color == Color.RED;
        }

        public void setRed() {
            this.color = Color.RED;
        }

        public boolean isBlack() {
            return this.color == Color.BLACK;
        }

        public void setBlack() {
            this.color = Color.BLACK;
        }

        public void print(Consumer<Node<T>> printer, int maxHeight, int i) {
            String prefix = "-".repeat(maxHeight - i);
            if (this.isNil()) {
                System.out.println("NIL");
            } else {
                printer.accept(this);
            }
            if (this.left != null) {
                System.out.print(prefix + "Left:  ");
                this.left.print(printer, maxHeight, i - 1);
            }
            if (this.right != null) {
                System.out.print(prefix + "Right: ");
                this.right.print(printer, maxHeight, i - 1);
            }
        }

        public enum Color {
            RED, BLACK
        }
    }
}
