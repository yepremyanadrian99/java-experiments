package experiments.pattern.decorator;

import experiments.Experiment;
import experiments.pattern.decorator.beverage.Beverage;
import experiments.pattern.decorator.beverage.Cappuccino;
import experiments.pattern.decorator.beverage.Espresso;
import experiments.pattern.decorator.beverage.addon.ChocolateAddon;
import experiments.pattern.decorator.beverage.addon.MilkAddon;
import experiments.pattern.decorator.beverage.machine.BeverageMachine;

public class DecoratorExperiment extends Experiment {

    @Override
    protected void execute() {
        experiment1();
        experiment2();
    }

    private void experiment1() {
        Beverage espresso = new Espresso();
        Beverage espressoWithMilk = new MilkAddon(espresso);
        Beverage espressoWithChocolate = new ChocolateAddon(espresso);
        Beverage espressoWithMilkAndChocolate = new ChocolateAddon(espressoWithMilk);
        Beverage espressoWithMilkAndExtraChocolate = new ChocolateAddon(espressoWithMilkAndChocolate);
        System.out.println("Espresso: " + espresso.getCost() + "$");
        System.out.println("Espresso with milk: " + espressoWithMilk.getCost() + "$");
        System.out.println("Espresso with chocolate: " + espressoWithChocolate.getCost() + "$");
        System.out.println("Espresso with milk and chocolate: " + espressoWithMilkAndChocolate.getCost() + "$");
        System.out.println("Espresso with milk and extra chocolate: " + espressoWithMilkAndExtraChocolate.getCost() + "$");
    }

    private void experiment2() {
        BeverageMachine beverageMachine = new BeverageMachine();
        beverageMachine.selectBeverage(new Cappuccino())
                .addSugar()
                .addMilk()
                .addChocolate()
                .addChocolate()
                .makeBeverage();
    }
}

