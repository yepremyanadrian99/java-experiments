package experiments.datastructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import experiments.Experiment;
import experiments.utils.Utils;

public class HeapExperiment extends Experiment {

    @Override
    protected void execute() {
        List<Integer> list = Arrays.asList(3, 9, 2, 1, 4, 5, -200, 32, -51, 123, 0, 3, 9, 3, 15);
        Heap<Integer> maxHeap = new Heap<>(list, true);
        Heap<Integer> minHeap = new Heap<>(list, false);

        System.out.println("Original: " + list);
        System.out.println("MaxHeap: " + maxHeap.getList());
        System.out.println("MinHeap: " + minHeap.getList());

        int maxValue = maxHeap.peekRoot();
        int minValue = minHeap.peekRoot();

        System.out.println("Max is: " + maxValue + ", works correctly: " + (maxValue == Collections.max(list)));
        System.out.println("Min is: " + minValue + ", works correctly: " + (minValue == Collections.min(list)));

        List<Integer> ascSortedArray = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ascSortedArray.add(minHeap.extractRoot());
        }

        List<Integer> descSortedArray = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            descSortedArray.add(maxHeap.extractRoot());
        }

        System.out.println("Ascending sorted array: " + ascSortedArray);
        System.out.println("Ascending sorted array is sorted correctly: " + Utils.isAscSorted(ascSortedArray));
        System.out.println("Descending sorted array: " + descSortedArray);
        System.out.println("Descending sorted array is sorted correctly: " + Utils.isDescSorted(descSortedArray));
    }
}
