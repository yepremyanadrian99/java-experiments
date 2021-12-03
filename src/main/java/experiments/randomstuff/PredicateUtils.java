package experiments.randomstuff;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class PredicateUtils {

    private PredicateUtils() {
    }

    public static <T> Predicate<T> truePredicate() {
        return object -> true;
    }

    public static <T> Predicate<T> falsePredicate() {
        return Predicate.not(truePredicate());
    }

    public static <T> Predicate<T> or(Collection<Predicate<T>> predicates) {
        return operateOnPredicates(predicates, Predicate::or);
    }

    @SafeVarargs
    public static <T> Predicate<T> or(Predicate<T>... predicates) {
        return or(Arrays.asList(predicates));
    }

    public static <T> Predicate<T> and(Collection<Predicate<T>> predicates) {
        return operateOnPredicates(predicates, Predicate::and);
    }

    @SafeVarargs
    public static <T> Predicate<T> and(Predicate<T>... predicates) {
        return and(Arrays.asList(predicates));
    }

    private static <T> Predicate<T> operateOnPredicates(Collection<Predicate<T>> predicates,
                                                        BinaryOperator<Predicate<T>> operator) {
        return Optional.ofNullable(predicates)
                .orElse(List.of())
                .stream()
                .reduce(operator)
                .orElse(truePredicate());
    }
}
