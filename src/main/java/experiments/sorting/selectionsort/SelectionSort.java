package experiments.sorting.selectionsort;

import java.util.Collections;
import java.util.List;

import experiments.sorting.common.SortingAlgorithm;

public class SelectionSort<T extends Comparable<T>> implements SortingAlgorithm<T, List<T>> {

    @Override
    public void sort(List<T> list) {
        for (int i = 0; i < list.size() - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < list.size(); ++j) {
                if (list.get(j).compareTo(list.get(min)) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                Collections.swap(list, i, min);
            }
        }
    }
}
