import java.util.ArrayList;
import java.util.Arrays;

import expirements.dataStructure.AVLExperiment;
import expirements.executor.ExperimentExecutor;
import expirements.pattern.strategy.StrategyPatternExperiment;
import expirements.sorting.BubbleSortExperiment;
import expirements.sorting.BucketSortExperiment;
import expirements.sorting.InsertionSortExperiment;
import expirements.sorting.MergeSortExperiment;
import expirements.sorting.QuickSortExperiment;
import expirements.sorting.SelectionSortExperiment;

public class MainApplication {

    public static void main(String[] args) {
        new ExperimentExecutor()
            .add(new BubbleSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))))
            .add(new SelectionSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))))
            .add(new InsertionSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))))
            .add(new MergeSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))))
            .add(new QuickSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))))
            .add(new BucketSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))))
            .add(new BucketSortExperiment(new ArrayList<>(Arrays.asList(5, 8, 6, 8, 0, 8, 10, 0, 8, 5, 15, 10))))
            .add(new AVLExperiment())
            .add(new StrategyPatternExperiment())
            .startAll();
    }
}
