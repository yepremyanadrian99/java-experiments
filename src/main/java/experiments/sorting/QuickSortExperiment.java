package experiments.sorting;

import java.util.Collections;
import java.util.List;

import experiments.Experiment;

public class QuickSortExperiment extends Experiment {

    private final List<Integer> list;

    public QuickSortExperiment(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        quickSort(0, list.size() - 1);
    }

    @Override
    protected void afterExecute() {
        System.out.println("  Sorted list: " + list);
        super.afterExecute();
    }

    private void quickSort(int begin, int end) {
        if (begin >= end) {
            return;
        }
        int p = partition(begin, end);
        quickSort(begin, p);
        quickSort(p + 1, end);
    }

    private int partition(int begin, int end) {
        int pivot = list.get((begin + end) / 2);
        int i = begin - 1;
        int j = end + 1;
        while (true) {
            do ++i;
            while (list.get(i) < pivot);
            do --j;
            while (list.get(j) > pivot);
            if (i >= j) {
                return j;
            }
            Collections.swap(list, i, j);
        }
    }
}
