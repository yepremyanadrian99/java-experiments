package experiments.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class CustomRecursiveAction extends RecursiveAction {

    private static final int THRESHOLD = 4;

    private final String workload;

    public CustomRecursiveAction(String workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload.length() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            processing(workload);
        }
    }

    private List<RecursiveAction> createSubtasks() {
        String workload1 = workload.substring(0, workload.length() / 2);
        String workload2 = workload.substring(workload.length() / 2);
        return List.of(
                new CustomRecursiveAction(workload1),
                new CustomRecursiveAction(workload2)
        );
    }

    private void processing(String work) {
        System.out.printf("Processing result with thread: %s\n", Thread.currentThread().getName());
        System.out.println(work.toUpperCase());
    }
}
