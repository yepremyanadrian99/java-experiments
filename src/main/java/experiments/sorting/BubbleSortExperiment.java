package experiments.sorting;

import java.util.Collections;
import java.util.List;

import experiments.Experiment;

public class BubbleSortExperiment extends Experiment {

    private final List<Integer> list;

    public BubbleSortExperiment(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        bubbleSort();
    }

    @Override
    protected void afterExecute() {
        System.out.println("  Sorted list: " + list);
        super.afterExecute();
    }

    private void bubbleSort() {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; ++i) {
                int current = i;
                if (list.get(current) > list.get(current + 1)) {
                    Collections.swap(list, current, ++current);
                    isSorted = false;
                }
            }
        }
    }
}
