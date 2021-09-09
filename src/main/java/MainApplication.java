import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import experiments.Experiment;
import experiments.dataStructure.AVLExperiment;
import experiments.executor.ExperimentExecutor;
import experiments.pattern.decorator.DecoratorExperiment;
import experiments.pattern.strategy.StrategyPatternExperiment;
import experiments.sorting.BubbleSortExperiment;
import experiments.sorting.BucketSortExperiment;
import experiments.sorting.InsertionSortExperiment;
import experiments.sorting.MergeSortExperiment;
import experiments.sorting.QuickSortExperiment;
import experiments.sorting.SelectionSortExperiment;

public class MainApplication {

    public static void main(String[] args) {
        new ExperimentExecutor()
            .addAll(sortingExperiments())
            .addAll(dataStructureExperiments())
            .addAll(patternExperiments())
            .startAll();
    }

    private static List<Experiment> sortingExperiments() {
        return List.of(
            new BubbleSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))),
            new SelectionSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))),
            new InsertionSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))),
            new MergeSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))),
            new QuickSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10))),
            new BucketSortExperiment(new ArrayList<>(Arrays.asList(5, 8, 6, 8, 0, 8, 10, 0, 8, 5, 15, 10))),
            new BucketSortExperiment(new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10)))
        );
    }

    private static List<Experiment> dataStructureExperiments() {
        return List.of(
            new AVLExperiment()
        );
    }

    private static List<Experiment> patternExperiments() {
        return List.of(
            new StrategyPatternExperiment(),
            new DecoratorExperiment()
        );
    }
}
