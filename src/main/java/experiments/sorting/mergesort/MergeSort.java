package experiments.sorting.mergesort;

import java.util.ArrayList;
import java.util.List;

import experiments.sorting.common.SortingAlgorithm;

public class MergeSort<T extends Comparable<T>> implements SortingAlgorithm<T, List<T>> {

    @Override
    public void sort(List<T> list) {
        mergeSort(list, 0, list.size() - 1);
    }

    private void mergeSort(List<T> list, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int middle = (begin + end) / 2;
        mergeSort(list, begin, middle);
        mergeSort(list, middle + 1, end);
        merge(list, begin, middle, end);
    }

    private void merge(List<T> list, int begin, int middle, int end) {
        int l, r, k;
        l = k = begin;
        r = middle + 1;
        List<T> tmp = new ArrayList<>(list);
        while (l <= middle && r <= end) {
            list.set(k++, tmp.get(l).compareTo(tmp.get(r)) < 0 ? tmp.get(l++) : tmp.get(r++));
        }
        while (l <= middle) {
            list.set(k++, tmp.get(l++));
        }
    }
}
