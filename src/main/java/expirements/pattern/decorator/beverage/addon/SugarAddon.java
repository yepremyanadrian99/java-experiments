package expirements.pattern.decorator.beverage.addon;

import expirements.pattern.decorator.beverage.Beverage;

public class SugarAddon extends BeverageAddon {

    public SugarAddon(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int getCost() {
        return this.beverage.getCost() + 1;
    }
}
