package expirements.pattern.decorator.beverage.addon;

import expirements.pattern.decorator.beverage.Beverage;

public class MilkAddon extends BeverageAddon {

    public MilkAddon(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int getCost() {
        return this.beverage.getCost() + 2;
    }
}
