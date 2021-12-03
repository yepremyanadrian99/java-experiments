package experiments.datastructure.heap;

import java.util.Comparator;
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
}
