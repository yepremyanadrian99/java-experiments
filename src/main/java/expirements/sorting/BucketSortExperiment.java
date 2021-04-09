package expirements.sorting;

import java.util.Collections;
import java.util.List;

import expirements.Experiment;

public class BucketSortExperiment extends Experiment {

    private final List<Integer> list;

    public BucketSortExperiment(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        for (int i : list) {
            assert i >= 0 : "For BucketSort to work properly, all elements must be non-negative.";
        }
        System.out.println("Unsorted list: " + list);
    }

    @Override
    protected void execute() {
        int maxElem = Collections.max(list, Integer::compareTo);
        bucketSort(maxElem);
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
}
