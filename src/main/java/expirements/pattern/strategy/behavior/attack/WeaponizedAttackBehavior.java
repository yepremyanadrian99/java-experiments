package expirements.pattern.strategy.behavior.attack;

import expirements.pattern.strategy.weapon.Weapon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeaponizedAttackBehavior implements AttackBehavior {

    private Weapon weapon;

    @Override
    public void attack() {
        System.out.println("Attacking with " + weapon.getName());
    }
}
