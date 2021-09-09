package expirements.pattern.decorator.beverage.machine;

import expirements.pattern.decorator.beverage.Beverage;
import expirements.pattern.decorator.beverage.addon.ChocolateAddon;
import expirements.pattern.decorator.beverage.addon.MilkAddon;
import expirements.pattern.decorator.beverage.addon.SugarAddon;

public class BeverageMachine {

    public BeverageMachine selectBeverage(Beverage beverage) {
        return new BeverageMachineSelected(beverage);
    }

    public BeverageMachine addMilk() {
        throw new RuntimeException("Beverage is not selected");
    }

    public BeverageMachine addSugar() {
        throw new RuntimeException("Beverage is not selected");
    }

    public BeverageMachine addChocolate() {
        throw new RuntimeException("Beverage is not selected");
    }

    public Beverage makeBeverage() {
        throw new RuntimeException("Beverage is not selected");
    }

    private static class BeverageMachineSelected extends BeverageMachine {

        private Beverage beverage;

        private BeverageMachineSelected(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public BeverageMachine.BeverageMachineSelected addMilk() {
            this.beverage = new MilkAddon(this.beverage);
            return this;
        }

        @Override
        public BeverageMachine.BeverageMachineSelected addSugar() {
            this.beverage = new SugarAddon(this.beverage);
            return this;
        }

        @Override
        public BeverageMachine.BeverageMachineSelected addChocolate() {
            this.beverage = new ChocolateAddon(this.beverage);
            return this;
        }

        @Override
        public Beverage makeBeverage() {
            System.out.printf("Your %s will cost you %d$\n", this.beverage.getName(), this.beverage.getCost());
            return this.beverage;
        }
    }
}
