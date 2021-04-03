import java.util.Arrays;

import expirements.executor.ExperimentExecutor;
import expirements.sorting.BubbleSortExperiment;
import expirements.sorting.InsertionSortExperiment;
import expirements.sorting.MergeSortExperiment;
import expirements.sorting.SelectionSortExperiment;

public class MainApplication {

    public static void main(String[] args) {
        new ExperimentExecutor()
            .add(new BubbleSortExperiment(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10)))
            .add(new SelectionSortExperiment(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10)))
            .add(new InsertionSortExperiment(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10)))
            .add(new MergeSortExperiment(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10)))
            .startAll();
    }
}
