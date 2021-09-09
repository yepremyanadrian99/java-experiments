package experiments.pattern.strategy;

import java.util.List;

import experiments.Experiment;
import experiments.pattern.strategy.behavior.attack.WeaponizedAttackBehavior;
import experiments.pattern.strategy.behavior.fly.WingedFlyBehavior;
import experiments.pattern.strategy.monster.Character;
import experiments.pattern.strategy.weapon.Claw;
import experiments.pattern.strategy.weapon.Katana;

public class StrategyPatternExperiment extends Experiment {

    @Override
    protected void execute() {
        getCharacters().forEach(character -> {
            character.attack();
            character.fly();
        });
    }

    private List<Character> getCharacters() {
        Character ninja = new Character();
        ninja.setAttackBehavior(new WeaponizedAttackBehavior(new Katana()));
        Character puppy = new Character();
        Character dragon = new Character();
        dragon.setAttackBehavior(new WeaponizedAttackBehavior(new Claw()));
        dragon.setFlyBehavior(new WingedFlyBehavior());
        return List.of(
            ninja,
            puppy,
            dragon
        );
    }
}
