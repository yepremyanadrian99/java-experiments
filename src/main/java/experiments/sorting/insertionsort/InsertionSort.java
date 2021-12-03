package experiments.sorting.insertionsort;

import java.util.List;

import experiments.sorting.common.SortingAlgorithm;

public class InsertionSort<T extends Comparable<T>> implements SortingAlgorithm<T, List<T>> {

    @Override
    public void sort(List<T> list) {
        for (int i = 1; i < list.size(); ++i) {
            T key = list.get(i);
            int j = i - 1;
            for (; j >= 0 && list.get(j).compareTo(key) > 0; --j) {
                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, key);
        }
    }
}
