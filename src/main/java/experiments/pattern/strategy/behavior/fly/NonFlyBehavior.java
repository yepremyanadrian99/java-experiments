package experiments.pattern.strategy.behavior.fly;

public class NonFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("Can't fly");
    }
}
