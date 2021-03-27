package expirements.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import expirements.Experiment;

public class ForkJoinExperiment extends Experiment {

    public ForkJoinExperiment(String work) {
        super(work);
    }

    @Override
    public void execute() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        RecursiveAction task = new CustomRecursiveAction(args[0].toString());
        Void invokeResult = pool.invoke(task);
        // 1. invoke() - submits and joins on that task and return final result.
        // 2. submit() - externalSubmits a task for execution. Need to join the ForkJoinTask returned,
        //    otherwise after main thread is closed the daemon thread will stop it's execution abruptly.
        //    ForkJoinTask<Void> submitResult = pool.submit(task);
        // 3. execute() - externalSubmits a task for asynchronous execution. Nothing is returned.
        //    pool.execute(task);
    }
}
