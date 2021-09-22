package experiments.dataStructure.redBlackTree;

import experiments.Experiment;

public class RedBlackTreeExperiment extends Experiment {

    @Override
    protected void execute() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(7);
        tree.insert(15);
        tree.insert(30);
        tree.insert(20);
        tree.insert(2);
        tree.insert(1);
        tree.insert(40);
        tree.insert(35);
        tree.print();
        tree.printAsArray();
    }
/*
  Should be before delete:

          5
       /     \
     2         7
   /   \     /   \
  1     4   6     15
       /         /   \
     3         9      16
*/
}
