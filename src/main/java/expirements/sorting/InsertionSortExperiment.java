package expirements.sorting;

import java.util.List;

import expirements.Experiment;

public class InsertionSortExperiment extends Experiment {

    private final List<Integer> list;

    public InsertionSortExperiment(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        insertionSort();
    }

    @Override
    protected void afterExecute() {
        System.out.println("  Sorted list: " + list);
        super.afterExecute();
    }

    private void insertionSort() {
        for (int i = 1; i < list.size(); ++i) {
            int key = list.get(i);
            int j = i - 1;
            for (; j >= 0 && list.get(j) > key; --j) {
                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, key);
        }
    }
}
