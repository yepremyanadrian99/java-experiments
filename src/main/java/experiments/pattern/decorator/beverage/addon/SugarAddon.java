package experiments.pattern.decorator.beverage.addon;

import experiments.pattern.decorator.beverage.Beverage;

public class SugarAddon extends BeverageAddon {

    public SugarAddon(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int getCost() {
        return this.beverage.getCost() + 1;
    }
}
