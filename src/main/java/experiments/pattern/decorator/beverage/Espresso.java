package experiments.pattern.decorator.beverage;

public class Espresso implements Beverage {

    @Override
    public String getName() {
        return "Espresso";
    }

    @Override
    public int getCost() {
        return 2;
    }
}
