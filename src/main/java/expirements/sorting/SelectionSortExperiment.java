package expirements.sorting;

import java.util.Collections;
import java.util.List;

import expirements.Experiment;

public class SelectionSortExperiment extends Experiment {

    private final List<Integer> list;

    public SelectionSortExperiment(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        selectionSort();
    }

    @Override
    protected void afterExecute() {
        System.out.println("  Sorted list: " + list);
        super.afterExecute();
    }

    private void selectionSort() {
        for (int i = 0; i < list.size() - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < list.size(); ++j) {
                if (list.get(j) < list.get(min)) {
                    min = j;
                }
            }
            if (min != i) {
                Collections.swap(list, i, min);
            }
        }
    }
}
