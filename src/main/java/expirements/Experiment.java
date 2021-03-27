package expirements;

public abstract class Experiment {

    protected Object[] args;

    public Experiment(Object... args) {
        this.args = args;
    }

    public void start() {
        beforeExecute();
        execute();
        afterExecute();
    }

    protected void beforeExecute() {
        System.out.printf("Starting %s...%n", getClass().getSimpleName());
    }

    protected abstract void execute();

    protected void afterExecute() {
        System.out.printf("Finished %s.%n", getClass().getSimpleName());
    }
}
