package experiments.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Utils {

    private Utils() {
    }

    public static List<Integer> getNewPositiveList() {
        return new ArrayList<>(Arrays.asList(5, 8, 6, 8, 0, 8, 10, 0, 8, 5, 15, 10));
    }

    public static List<Integer> getNewMixedList() {
        return new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10));
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
