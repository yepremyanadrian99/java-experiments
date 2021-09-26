package experiments.sorting.quickSort;

import java.util.Collections;
import java.util.List;

import experiments.sorting.common.SortingAlgorithm;

public class QuickSort<T extends Comparable<T>> implements SortingAlgorithm<T, List<T>> {

    @Override
    public void sort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(List<T> list, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int p = partition(list, begin, end);
        quickSort(list, begin, p);
        quickSort(list, p + 1, end);
    }

    private int partition(List<T> list, int begin, int end) {
        T pivot = list.get((begin + end) / 2);
        int i = begin - 1;
        int j = end + 1;
        while (true) {
            do ++i;
            while (list.get(i).compareTo(pivot) < 0);
            do --j;
            while (list.get(j).compareTo(pivot) > 0);
            if (i >= j) {
                return j;
            }
            Collections.swap(list, i, j);
        }
    }
}
