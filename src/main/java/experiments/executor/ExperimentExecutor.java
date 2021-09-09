package experiments.executor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import experiments.Experiment;

public class ExperimentExecutor {

    private final List<Experiment> experiments;

    public ExperimentExecutor() {
        this.experiments = new LinkedList<>();
    }

    public ExperimentExecutor add(Experiment experiment) {
        this.experiments.add(experiment);
        return this;
    }

    public ExperimentExecutor addAll(Collection<? extends Experiment> experiments) {
        this.experiments.addAll(experiments);
        return this;
    }

    public ExperimentExecutor clear() {
        this.experiments.clear();
        return this;
    }

    public ExperimentExecutor startAll() {
        this.experiments.forEach(Experiment::start);
        return this;
    }
}
