package experiments.sorting.common;

import java.util.Collection;

public interface SortingAlgorithm<T extends Comparable<T>, C extends Collection<T>> {

    void sort(C collection);
}
