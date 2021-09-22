package experiments.dataStructure.redBlackTree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@SuppressWarnings({"rawtypes", "unchecked"})
public class RedBlackTree<T extends Comparable<T>> {

    private static final String CANT_BE_NULL_ERROR = "can't be null";
    private static final String GRAND_PARENT_IS_NULL_ERROR = "Grand parent " + CANT_BE_NULL_ERROR;
    private static final String UNCLE_IS_NULL_ERROR = "Grand parent " + CANT_BE_NULL_ERROR;

    public static final Node NIL = new Node(Node.BLACK);

    private Node<T> root = NIL;

    public void insert(T value) {
        this.root = internalInsert(null, this.root, value);
    }

    public void print() {
        this.root.print(this.root.blackHeight, this.root.blackHeight - 1);
    }

    public void printAsArray() {
        System.out.print("[ ");
        internalDFSPrint(this.root);
        System.out.print("]");
    }

    private void internalDFSPrint(Node<T> node) {
        if (node == NIL) {
            return;
        }
        internalDFSPrint(node.getLeft());
        System.out.print(node.value + " ");
        internalDFSPrint(node.getRight());
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
        if (this.root.isNil() || this.root == node || parent == null) {
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
    @ToString(exclude = {"parent", "left", "right"})
    public static class Node<T extends Comparable<T>> {

        private static final byte RED = 0;
        private static final byte BLACK = 1;

        private T value;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;
        private int blackHeight = 0;
        private byte color;

        public Node(byte color) {
            this.color = color;
        }

        public Node(T value) {
            this(RED);
            this.value = value;
            this.left = NIL;
            this.right = NIL;
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

        public void setLeft(Node<T> left) {
            this.left = left;
            this.left.parent = this;
        }

        public void setRight(Node<T> right) {
            this.right = right;
            this.right.parent = this;
        }

        public boolean isNil() {
            return this == NIL;
        }

        public boolean isRed() {
            return this.color == RED;
        }

        public void setRed() {
            this.color = RED;
        }

        public boolean isBlack() {
            return this.color == BLACK;
        }

        public void setBlack() {
            this.color = BLACK;
        }

        public void print(int maxHeight, int i) {
            String prefix = "-".repeat(maxHeight - i);
            if (this.isNil()) {
                System.out.println("NIL");
            } else {
                System.out.println("V: " + this.value + " C: " + (this.color == RED ? "RED" : "BLACK") + " BH: " + this.blackHeight);
            }
            if (this.left != null) {
                System.out.print(prefix + "Left:  ");
                this.left.print(maxHeight, i - 1);
            }
            if (this.right != null) {
                System.out.print(prefix + "Right: ");
                this.right.print(maxHeight, i - 1);
            }
        }
    }
}
