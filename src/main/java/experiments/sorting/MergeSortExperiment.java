package experiments.sorting;

import java.util.ArrayList;
import java.util.List;

import experiments.Experiment;

public class MergeSortExperiment extends Experiment {

    private final List<Integer> list;

    public MergeSortExperiment(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        mergeSort(0, list.size() - 1);
    }

    @Override
    protected void afterExecute() {
        System.out.println("  Sorted list: " + list);
        super.afterExecute();
    }

    private void mergeSort(int begin, int end) {
        if (begin >= end) {
            return;
        }
        int middle = (begin + end) / 2;
        mergeSort(begin, middle);
        mergeSort(middle + 1, end);
        merge(begin, middle, end);
    }

    private void merge(int begin, int middle, int end) {
        int l, r, k;
        l = k = begin;
        r = middle + 1;
        List<Integer> tmp = new ArrayList<>(list);
        while (l <= middle && r <= end) {
            list.set(k++, tmp.get(l) < tmp.get(r) ? tmp.get(l++) : tmp.get(r++));
        }
        while (l <= middle) {
            list.set(k++, tmp.get(l++));
        }
    }
}
