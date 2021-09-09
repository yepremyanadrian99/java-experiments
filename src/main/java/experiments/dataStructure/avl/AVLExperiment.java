package experiments.dataStructure.avl;

import experiments.Experiment;

public class AVLExperiment extends Experiment {

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
        tree.print();
    }
/*
      Should be:

          5
       /     \
     2         7
   /   \     /   \
  1     4   6     15
       /         /   \
     3         9      16
*/
}
