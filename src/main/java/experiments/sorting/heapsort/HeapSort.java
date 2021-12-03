package experiments.sorting.heapsort;

import java.util.Collections;
import java.util.List;

import experiments.sorting.common.SortingAlgorithm;

public class HeapSort<T extends Comparable<T>> implements SortingAlgorithm<T, List<T>> {

    @Override
    public void sort(List<T> list) {
        // First heapify to have the max value at list[0].
        for (int i = list.size() / 2 - 1; i >= 0; --i) {
            heapify(list, i, list.size());
        }
        for (int i = list.size() - 1; i > 0; --i) {
            // Move list[0] max value to the end.
            Collections.swap(list, 0, i);
            // Heapify until the swapped element.
            heapify(list, 0, i);
        }
    }

    private void heapify(List<T> list, int i, int n) {
        int max = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (left < n && list.get(left).compareTo(list.get(max)) > 0) {
            max = left;
        }
        if (right < n && list.get(right).compareTo(list.get(max)) > 0) {
            max = right;
        }
        if (i != max) {
            Collections.swap(list, i, max);
            heapify(list, max, n);
        }
    }
}
