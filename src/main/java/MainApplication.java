import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import experiments.Experiment;
import experiments.dataStructure.avlTree.AVLTreeExperiment;
import experiments.dataStructure.hashMap.SimpleHashMapExperiment;
import experiments.dataStructure.hashSet.SimpleHashSetExperiment;
import experiments.dataStructure.heap.HeapExperiment;
import experiments.dataStructure.redBlackTree.RedBlackTreeExperiment;
import experiments.executor.ExperimentExecutor;
import experiments.pattern.decorator.DecoratorExperiment;
import experiments.pattern.strategy.StrategyPatternExperiment;
import experiments.problem.admissibleOverpayment.AdmissibleOverpaymentExperiment;
import experiments.problem.bstInorderSuccessor.BSTInorderSuccessorExperiment;
import experiments.problem.cryptSum.CryptSumExperiment;
import experiments.problem.rotateMatrix.RotateMatrixExperiment;
import experiments.problem.smallestSubstring.SmallestSubstringExperiment;
import experiments.problem.sudokuChecker.SudokuCheckerExperiment;
import experiments.sorting.bubbleSort.BubbleSort;
import experiments.sorting.common.SortingAlgorithm;
import experiments.sorting.common.SortingExperiment;
import experiments.sorting.countingSort.CountingSort;
import experiments.sorting.heapSort.HeapSort;
import experiments.sorting.insertionSort.InsertionSort;
import experiments.sorting.mergeSort.MergeSort;
import experiments.sorting.quickSort.QuickSort;
import experiments.sorting.selectionSort.SelectionSort;
import experiments.utils.Utils;

public class MainApplication {

    public static void main(String[] args) {
        new ExperimentExecutor()
            .addAll(sortingExperiments())
            .addAll(dataStructureExperiments())
            .addAll(patternExperiments())
            .addAll(problemExperiments())
            .clear()
            .add(new SmallestSubstringExperiment())
            .startAll();
    }

    private static List<Experiment> sortingExperiments() {
        return Stream.concat(
            getStreamOfExperiments(Utils::getNewPositiveList),
            getStreamOfExperiments(Utils::getNewMixedList)
        ).collect(Collectors.toList());
    }

    private static List<Experiment> dataStructureExperiments() {
        return List.of(
            new AVLTreeExperiment(),
            new RedBlackTreeExperiment(),
            new SimpleHashMapExperiment(),
            new SimpleHashSetExperiment(),
            new HeapExperiment()
        );
    }

    private static List<Experiment> patternExperiments() {
        return List.of(
            new StrategyPatternExperiment(),
            new DecoratorExperiment()
        );
    }

    private static List<Experiment> problemExperiments() {
        return List.of(
            new RotateMatrixExperiment(),
            new SudokuCheckerExperiment(),
            new CryptSumExperiment(),
            new AdmissibleOverpaymentExperiment(),
            new BSTInorderSuccessorExperiment()
        );
    }

    private static Stream<SortingExperiment<Integer>> getStreamOfExperiments(Supplier<List<Integer>> listSupplier) {
        return Stream.<SortingAlgorithm<Integer, List<Integer>>>of(
            new BubbleSort<>(),
            new SelectionSort<>(),
            new InsertionSort<>(),
            new MergeSort<>(),
            new QuickSort<>(),
            new CountingSort(),
            new HeapSort<>()
        ).map(algorithm -> new SortingExperiment<>(listSupplier.get(), algorithm));
    }
}
