package experiments.pattern.decorator.beverage.addon;

import experiments.pattern.decorator.beverage.Beverage;

public class MilkAddon extends BeverageAddon {

    public MilkAddon(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int getCost() {
        return this.beverage.getCost() + 2;
    }
}
