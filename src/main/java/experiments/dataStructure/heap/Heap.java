package experiments.dataStructure.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Heap<T extends Comparable<T>> {

    public static final Predicate<Integer> MAX_HEAP = comparison -> comparison > 0;
    public static final Predicate<Integer> MIN_HEAP = comparison -> comparison < 0;

    private final List<T> list;
    private Predicate<Integer> heapType;

    public Heap(boolean maxHeap) {
        this.list = new ArrayList<>();
        this.heapType = maxHeap ? MAX_HEAP : MIN_HEAP;
    }

    public Heap(Collection<T> collection, boolean maxHeap) {
        this(maxHeap);
        for (T value : collection) {
            insert(value);
        }
    }

    public void changeHeapType(Predicate<Integer> heapType) {
        this.heapType = heapType;
        heapify();
    }

    public T peekRoot() {
        return isEmpty() ? null : list.get(0);
    }

    public void insert(T value) {
        list.add(value);
        heapify();
    }

    public T extractRoot() {
        if (isEmpty()) {
            return null;
        }
        return removeAt(0);
    }

    public T remove(T value) {
        return removeAt(list.indexOf(value));
    }

    public T removeAt(int index) {
        if (index < 0) {
            return null;
        }
        int lastIndex = list.size() - 1;
        if (index == lastIndex) {
            return list.remove(index);
        }
        Collections.swap(list, index, lastIndex);
        T removedValue = list.remove(lastIndex);
        heapify();
        return removedValue;
    }

    public boolean isEmpty() {
        return list == null || list.isEmpty();
    }

    public List<T> getList() {
        return list;
    }

    private void heapify() {
        for (int i = list.size() / 2 - 1; i >= 0; --i) {
            internalHeapify(i);
        }
    }

    private void internalHeapify(int i) {
        AtomicInteger atomicResultIndex = new AtomicInteger(i);
        int left = Utils.leftChild(i);
        int right = Utils.rightChild(i);
        Utils.swapIndicesIf(list, atomicResultIndex, left, Comparable::compareTo, heapType);
        Utils.swapIndicesIf(list, atomicResultIndex, right, Comparable::compareTo, heapType);
        int resultIndex = atomicResultIndex.get();
        if (resultIndex != i) {
            Collections.swap(list, resultIndex, i);
            internalHeapify(resultIndex);
        }
    }
}
