package experiments.sorting.countingsort;

import java.util.Collections;
import java.util.List;

import experiments.sorting.common.SortingAlgorithm;

public class CountingSort implements SortingAlgorithm<Integer, List<Integer>> {

    @Override
    public void sort(List<Integer> list) {
        int minElem = Collections.min(list, Integer::compareTo);
        int maxElem = Collections.max(list, Integer::compareTo);
        if (minElem >= 0) {
            // Positive-Only array elements.
            internalSort(list, maxElem);
        } else {
            // Negative-Positive array elements.
            internalSort(list, minElem, maxElem);
        }
    }

    private void internalSort(List<Integer> list, int maxElem) {
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

    private void internalSort(List<Integer> list, int minElem, int maxElem) {
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
