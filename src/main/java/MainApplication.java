import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expirements.Experiment;
import expirements.dataStructure.AVLExperiment;
import expirements.executor.ExperimentExecutor;
import expirements.pattern.decorator.DecoratorExperiment;
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
