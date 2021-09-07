package expirements.pattern.strategy.monster;

import expirements.pattern.strategy.behavior.attack.AttackBehavior;
import expirements.pattern.strategy.behavior.attack.NonAttackBehavior;
import expirements.pattern.strategy.behavior.fly.FlyBehavior;
import expirements.pattern.strategy.behavior.fly.NonFlyBehavior;
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
