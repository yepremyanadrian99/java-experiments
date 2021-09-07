package expirements.pattern.strategy.behavior.fly;

public class WingedFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("Flying with wings");
    }
}
