package expirements.pattern.decorator.beverage;

public class Cappuccino implements Beverage {

    @Override
    public String getName() {
        return "Cappuccino";
    }

    @Override
    public int getCost() {
        return 4;
    }
}
