package expirements.parallelStream;

import java.util.stream.Stream;

import expirements.Experiment;

public class ParallelStreamExperiment extends Experiment {

    @Override
    public void execute() {
        Stream.of(1, 2, 3, 4, 5)
            .parallel()
            .forEach(System.out::println);
    }
}
