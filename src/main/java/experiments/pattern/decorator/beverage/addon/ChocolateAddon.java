package experiments.pattern.decorator.beverage.addon;

import experiments.pattern.decorator.beverage.Beverage;

public class ChocolateAddon extends BeverageAddon {

    public ChocolateAddon(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int getCost() {
        return this.beverage.getCost() + 5;
    }
}
