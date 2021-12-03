package experiments.datastructure.redblacktree;

import java.util.List;

import experiments.Experiment;

public class RedBlackTreeExperiment extends Experiment {

    private static final List<Integer> EXPECTED_RESULT = List.of(
            1, 2, 7, 10, 15, 20, 30, 35, 40
    );

    @Override
    protected void execute() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(10);
        tree.insert(10);
        tree.insert(10);
        tree.insert(7);
        tree.insert(15);
        tree.insert(30);
        tree.insert(20);
        tree.insert(2);
        tree.insert(1);
        tree.insert(40);
        tree.insert(35);
        tree.printAsTree();
        List<Integer> result = tree.asList();
        System.out.println(result);
        System.out.println("Red-Black Tree works correctly: " + EXPECTED_RESULT.equals(result));
        System.out.println("DFS: " + tree.dfs());
        System.out.println("BFS: " + tree.bfs());
    }
}
