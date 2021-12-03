package experiments.randomstuff;

import experiments.Experiment;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static experiments.randomstuff.PredicateUtils.and;
import static experiments.randomstuff.PredicateUtils.or;

public class PredicateUtilsExperiment extends Experiment {

    private static final int FROM = -10;
    private static final int TO = 10;

    private Collection<Integer> collection;

    public PredicateUtilsExperiment() {
        this.collection = IntStream.range(FROM, TO + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    protected void execute() {
        collection = collection.stream()
                .filter(
                        or(
                                and(
                                        PredicateUtilsExperiment::isPositive,
                                        PredicateUtilsExperiment::isEven
                                ),
                                and(
                                        PredicateUtilsExperiment::isNegative,
                                        PredicateUtilsExperiment::isOdd
                                ),
                                PredicateUtilsExperiment::isZero
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    protected void beforeExecute() {
        super.beforeExecute();
        System.out.println("Unfiltered collection: " + collection);
        System.out.printf(
                "Filtering the collection for it to possibly contain "
                        + "only positive even numbers, negative odd numbers and 0 "
                        + "in range [%d, %d].\n",
                FROM,
                TO
        );
    }

    @Override
    protected void afterExecute() {
        Collection<Integer> correctCollection = collection.stream()
                .filter(i -> isPositive(i) && isEven(i)
                        || isNegative(i) && isOdd(i)
                        || isZero(i)
                )
                .collect(Collectors.toList());
        boolean worksCorrectly = CollectionUtils.isEqualCollection(collection, correctCollection);
        System.out.println("Filtered collection: " + collection);
        System.out.println("Correctly filtered collection: " + correctCollection);
        System.out.println("Filtering works correctly: " + worksCorrectly);
        super.afterExecute();
    }

    private static boolean isZero(Integer i) {
        return i == 0;
    }

    private static boolean isPositive(Integer i) {
        return i > 0;
    }

    private static boolean isNegative(Integer i) {
        return i < 0;
    }

    private static boolean isEven(Integer i) {
        return i % 2 == 0;
    }

    private static boolean isOdd(Integer i) {
        return !isEven(i);
    }
}
