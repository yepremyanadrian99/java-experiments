package experiments;

public abstract class Experiment {

    protected final Object[] args;

    public Experiment(Object... args) {
        this.args = args;
    }

    public void start() {
        beforeExecute();
        execute();
        afterExecute();
    }

    protected abstract void execute();

    protected void beforeExecute() {
        System.out.printf("Starting %s...%n", getClass().getSimpleName());
    }

    protected void afterExecute() {
        System.out.printf("Finished %s.%n%n", getClass().getSimpleName());
    }
}
