package experiments.problem.smallestSubstring;

import java.util.Arrays;

import experiments.Experiment;

public class SmallestSubstringExperiment extends Experiment {

    @Override
    protected void execute() {
        char[] arr = {'x', 'y', 'z'};
        String str = "xyyzyzyx";
        Arrays.sort(arr);
        for (int i = 0; i <= str.length() - arr.length; ++i) {
            String output = str.substring(i, i + arr.length);
            char[] sub = output.toCharArray();
            Arrays.sort(sub);
            if (Arrays.equals(arr, sub)) {
                System.out.println(output);
                return;
            }
        }
    }
}
