package experiments.sorting.common;

import java.util.List;

import experiments.Experiment;
import experiments.utils.Utils;

public class SortingExperiment<T extends Comparable<T>> extends Experiment {

    private final List<T> list;
    private final SortingAlgorithm<T, List<T>> algorithm;

    public SortingExperiment(List<T> list, SortingAlgorithm<T, List<T>> algorithm) {
        this.list = list;
        this.algorithm = algorithm;
    }

    @Override
    protected void beforeExecute() {
        System.out.printf("Starting %s...%n", algorithm.getClass().getSimpleName());
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        algorithm.sort(list);
    }

    @Override
    protected void afterExecute() {
        System.out.println("  Sorted list: " + list);
        System.out.println("Sorting works correctly: " + Utils.isAscSorted(list));
        System.out.printf("Finished %s.%n%n", algorithm.getClass().getSimpleName());
    }
}
