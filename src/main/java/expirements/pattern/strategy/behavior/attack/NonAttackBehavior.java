package expirements.pattern.strategy.behavior.attack;

public class NonAttackBehavior implements AttackBehavior {

    @Override
    public void attack() {
        System.out.println("Can't attack");
    }
}
