package experiments.dataStructure.bst_generics_not_working.avlTree;

import java.util.List;

import experiments.Experiment;

public class AVLExperiment extends Experiment {

    private static final List<Integer> EXPECTED_RESULT = List.of(
        1, 2, 3, 4, 6, 7, 9, 15, 16
    );

    @Override
    protected void execute() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(5);
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(9);
        tree.insert(3);
        tree.insert(16);
        tree.insert(15);
        tree.insert(15);
        tree.delete(5);
        tree.printAsTree();
        List<Integer> result = tree.asList();
        System.out.println("AVL Tree works correctly: " + EXPECTED_RESULT.equals(result));
    }
}
