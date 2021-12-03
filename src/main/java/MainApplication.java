import experiments.Experiment;
import experiments.datastructure.avltree.AVLTreeExperiment;
import experiments.datastructure.hashmap.SimpleHashMapExperiment;
import experiments.datastructure.hashset.SimpleHashSetExperiment;
import experiments.datastructure.heap.HeapExperiment;
import experiments.datastructure.redblacktree.RedBlackTreeExperiment;
import experiments.executor.ExperimentExecutor;
import experiments.pattern.decorator.DecoratorExperiment;
import experiments.pattern.strategy.StrategyPatternExperiment;
import experiments.problem.admissibleOverpayment.AdmissibleOverpaymentExperiment;
import experiments.problem.bstInorderSuccessor.BSTInorderSuccessorExperiment;
import experiments.problem.cryptSum.CryptSumExperiment;
import experiments.problem.rotateMatrix.RotateMatrixExperiment;
import experiments.problem.smallestSubstring.SmallestSubstringExperiment;
import experiments.problem.sudokuChecker.SudokuCheckerExperiment;
import experiments.randomstuff.PredicateUtilsExperiment;
import experiments.sorting.bubblesort.BubbleSort;
import experiments.sorting.common.SortingAlgorithm;
import experiments.sorting.common.SortingExperiment;
import experiments.sorting.countingsort.CountingSort;
import experiments.sorting.heapsort.HeapSort;
import experiments.sorting.insertionsort.InsertionSort;
import experiments.sorting.mergesort.MergeSort;
import experiments.sorting.quicksort.QuickSort;
import experiments.sorting.selectionsort.SelectionSort;
import experiments.utils.Utils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApplication {

    public static void main(String[] args) {
        new ExperimentExecutor()
                .addAll(sortingExperiments())
                .addAll(dataStructureExperiments())
                .addAll(patternExperiments())
                .addAll(problemExperiments())
                .addAll(randomStuffExperiments())
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
                new BSTInorderSuccessorExperiment(),
                new SmallestSubstringExperiment()
        );
    }

    private static List<Experiment> randomStuffExperiments() {
        return List.of(
                new PredicateUtilsExperiment()
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
