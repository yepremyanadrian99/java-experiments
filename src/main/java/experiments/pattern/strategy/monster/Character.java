package experiments.pattern.strategy.monster;

import experiments.pattern.strategy.behavior.attack.AttackBehavior;
import experiments.pattern.strategy.behavior.attack.NonAttackBehavior;
import experiments.pattern.strategy.behavior.fly.FlyBehavior;
import experiments.pattern.strategy.behavior.fly.NonFlyBehavior;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Character {

    private AttackBehavior attackBehavior = new NonAttackBehavior();
    private FlyBehavior flyBehavior = new NonFlyBehavior();

    public void attack() {
        attackBehavior.attack();
    }

    public void fly() {
        flyBehavior.fly();
    }
}
