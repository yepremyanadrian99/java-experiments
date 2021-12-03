package experiments.sorting.bubblesort;

import java.util.Collections;
import java.util.List;

import experiments.sorting.common.SortingAlgorithm;

public class BubbleSort<T extends Comparable<T>> implements SortingAlgorithm<T, List<T>> {

    @Override
    public void sort(List<T> list) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; ++i) {
                int current = i;
                if (list.get(current).compareTo(list.get(current + 1)) > 0) {
                    Collections.swap(list, current, ++current);
                    isSorted = false;
                }
            }
        }
    }
}
