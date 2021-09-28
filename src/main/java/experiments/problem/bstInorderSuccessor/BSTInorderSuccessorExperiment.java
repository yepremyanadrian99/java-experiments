package experiments.problem.bstInorderSuccessor;

import experiments.Experiment;

public class BSTInorderSuccessorExperiment extends Experiment {

    @Override
    protected void execute() {
        BST<Integer> bst = new BST<>();
        bst.insert(20);
        bst.insert(9);
        bst.insert(25);
        bst.insert(5);
        bst.insert(12);
        bst.insert(11);
        bst.insert(14);
        bst.insert(10);
        System.out.println("Inorder for: " + 25 + " is: " + bst.inorderSuccessor(25));
        System.out.println("Inorder for: " + 5 + " is: " + bst.inorderSuccessor(5).getValue());
        System.out.println("Inorder for: " + 20 + " is: " + bst.inorderSuccessor(20).getValue());
        System.out.println("Inorder for: " + 14 + " is: " + bst.inorderSuccessor(14).getValue());
        System.out.println("Inorder for: " + 9 + " is: " + bst.inorderSuccessor(9).getValue());
        System.out.println(bst);
    }
}
