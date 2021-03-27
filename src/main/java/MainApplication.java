import expirements.executor.ExperimentExecutor;
import expirements.forkJoin.ForkJoinExperiment;

public class MainApplication {

    public static void main(String[] args) {
        new ExperimentExecutor()
            .clear()
            .add(new ForkJoinExperiment("adrian.yepremyan"))
            .startAll();
    }
}
