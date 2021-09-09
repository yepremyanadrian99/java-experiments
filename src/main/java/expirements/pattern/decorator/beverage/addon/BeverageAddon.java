package expirements.pattern.decorator.beverage.addon;

import expirements.pattern.decorator.beverage.Beverage;

public abstract class BeverageAddon implements Beverage {

    protected Beverage beverage;

    public BeverageAddon(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getName() {
        return this.beverage.getName();
    }
}
