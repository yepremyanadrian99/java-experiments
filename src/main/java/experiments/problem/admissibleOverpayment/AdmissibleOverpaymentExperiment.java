package experiments.problem.admissibleOverpayment;

import experiments.Experiment;

public class AdmissibleOverpaymentExperiment extends Experiment {

    @Override
    protected void execute() {
        boolean isAdmissible1 = isAdmissibleOverpayment(
                new double[]{
                        110,
                        95,
                        70
                },
                new String[]{
                        "10.0% higher than in-store",
                        "5.0% lower than in-store",
                        "Same as in-store"
                },
                5d
        );
        boolean isAdmissible2 = isAdmissibleOverpayment(
                new double[]{
                        48,
                        165
                },
                new String[]{
                        "20.0% lower than in-store",
                        "10.00% higher than in-store"
                },
                2d
        );
        System.out.println("Admissible overpayment works correctly: " + (isAdmissible1 && !isAdmissible2));
    }

    boolean isAdmissibleOverpayment(double[] prices, String[] notes, double x) {
        Object[][] convertedNotes = convert(notes);
        double sum = 0.0d;
        for (int i = 0; i < prices.length; ++i) {
            char priceChange = (char) convertedNotes[i][1];
            if (priceChange == 's') {
                continue;
            }
            double percentChange = (double) convertedNotes[i][0];
            double percent = 100.0d + percentChange * (priceChange == 'l' ? -1 : 1);
            double newValue = prices[i];
            double oldValue = 100 * newValue / percent;
            double diff = newValue - oldValue;
            sum += diff;
        }
        return sum <= x;
    }

    Object[][] convert(String[] notes) {
        Object[][] objects = new Object[notes.length][2];
        for (int i = 0; i < notes.length; ++i) {
            String[] strings = notes[i].split("% ");
            if (strings.length == 1) {
                objects[i][0] = 0.0d;
                objects[i][1] = 's';
            } else {
                objects[i][0] = Double.parseDouble(strings[0]);
                objects[i][1] = strings[1].charAt(0);
            }
        }
        return objects;
    }
}
