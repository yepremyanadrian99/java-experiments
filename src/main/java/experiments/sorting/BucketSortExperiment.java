package experiments.sorting;

import java.util.Collections;
import java.util.List;

import experiments.Experiment;

public class BucketSortExperiment extends Experiment {

    private final List<Integer> list;

    public BucketSortExperiment(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        int minElem = Collections.min(list, Integer::compareTo);
        int maxElem = Collections.max(list, Integer::compareTo);
        if (minElem >= 0) {
            bucketSort(maxElem);
        } else {
            bucketSortMixed(minElem, maxElem);
        }
    }

    @Override
    protected void afterExecute() {
        System.out.println("  Sorted list: " + list);
        super.afterExecute();
    }

    private void bucketSort(int maxElem) {
        int[] bucket = new int[maxElem + 1];
        for (int i = 0; i < list.size(); ++i) {
            bucket[i] = 0;
        }
        for (int i : list) {
            ++bucket[i];
        }
        list.clear();
        for (int i = 0; i < bucket.length; ++i) {
            for (int j = 0; j < bucket[i]; ++j) {
                list.add(i);
            }
        }
    }

    private void bucketSortMixed(int minElem, int maxElem) {
        int size = Math.abs(minElem) + Math.abs(maxElem) + 1;
        int[] bucket = new int[size];
        for (int i = 0; i < list.size(); ++i) {
            bucket[i] = 0;
        }
        for (int i : list) {
            ++bucket[i - minElem];
        }
        list.clear();
        for (int i = 0; i < bucket.length; ++i) {
            for (int j = 0; j < bucket[i]; ++j) {
                list.add(i + minElem);
            }
        }
    }
}
