package experiments.dataStructure.heap;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Utils {

    private Utils() {
    }

    public static int leftChild(int i) {
        return i * 2 + 1;
    }

    public static int rightChild(int i) {
        return i * 2 + 2;
    }

    public static <T extends Comparable<T>> void swapIndicesIf(List<T> array,
                                                               AtomicInteger atomicResultIndex, int indexToSwap,
                                                               Comparator<T> comparator, Predicate<Integer> predicate) {
        Optional.of(indexToSwap)
            .filter(index -> index < array.size())
            .map(array::get)
            .map(rightValue -> comparator.compare(rightValue, array.get(atomicResultIndex.get())))
            .filter(predicate)
            .ifPresent(shouldSwap -> atomicResultIndex.set(indexToSwap));
    }

    public static <T extends Comparable<T>> boolean isAscSorted(Collection<? extends T> collection) {
        return isSorted(collection, cmp -> cmp <= 0);
    }

    public static <T extends Comparable<T>> boolean isDescSorted(Collection<? extends T> collection) {
        return isSorted(collection, cmp -> cmp >= 0);
    }

    private static <T extends Comparable<T>> boolean isSorted(Collection<? extends T> collection, Predicate<Integer> predicate) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        Iterator<? extends T> iterator = collection.iterator();
        Comparable<T> previous = iterator.next();
        while (iterator.hasNext()) {
            T current = iterator.next();
            int comparison = previous.compareTo(current);
            if (!predicate.test(comparison)) {
                return false;
            }
        }
        return true;
    }
}
