package experiments.sorting.quicksort;

import experiments.sorting.common.SortingAlgorithm;

import java.util.List;

import static java.util.Collections.swap;

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
        quickSort(list, begin, p - 1);
        quickSort(list, p + 1, end);
    }

    /**
     * The last element is always chosen as the pivot.
     */
    private int partition(List<T> list, int begin, int end) {
        // choose initial pivot, e.g. first, last, median or random.
        T pivot = list.get(end);
        int i = begin - 1;
        // end is exclusive as we chose it as our initial pivot.
        for (int j = begin; j < end; ++j) {
            if (list.get(j).compareTo(pivot) <= 0) {
                ++i;
                // i and j will differ as many times as there were elements bigger than pivot,
                // so if all elements were less than the pivot - no actual swap will place.
                // otherwise, the bigger element will "move right" towards the pivot,
                // and eventually in the swap() command outside the loop - they will be swapped.
                swap(list, i, j);
            }
        }
        // i + 1 is the correct position of the initially chosen pivot
        // as i was incremented as many times as there were elements <= the pivot,
        // so basically (i - begin) is the offset of your pivot.
        swap(list, i + 1, end);
        // as the pivot is now in its guaranteed correct index, which is the i + 1
        // we return it as the pivotIndex for further recursive partitioning.
        return i + 1;
    }

    /**
     * The first element is always chosen as the pivot.
     */
    private int partitionFirstElement(List<T> list, int begin, int end) {
        T pivot = list.get(begin);
        int i = end + 1;
        for (int j = end; j > begin; --j) {
            if (list.get(j).compareTo(pivot) >= 0) {
                --i;
                swap(list, i, j);
            }
        }
        swap(list, i - 1, begin);
        return i - 1;
    }
}
