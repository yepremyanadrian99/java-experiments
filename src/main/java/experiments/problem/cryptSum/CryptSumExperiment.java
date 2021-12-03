package experiments.problem.cryptSum;

import java.util.HashMap;
import java.util.Map;

import experiments.Experiment;

public class CryptSumExperiment extends Experiment {

    @Override
    protected void execute() {
        String[] words = {"SEND", "MORE", "MONEY"};
        String[] wordsWrongNumber = {"HEY", "OMAR", "MONEY"};
        String[] wordsWrongSum = {"SEND", "MORE", "MONDAY"};
        char[][] solution = new char[][]{
                {'O', '0'},
                {'M', '1'},
                {'Y', '2'},
                {'A', '3'},
                {'H', '4'},
                {'E', '5'},
                {'N', '6'},
                {'D', '7'},
                {'R', '8'},
                {'S', '9'}
        };
        boolean result1 = crypt(words, solution);
        boolean result2 = crypt(wordsWrongNumber, solution);
        boolean result3 = crypt(wordsWrongSum, solution);
        System.out.println("Crypt sum works correctly: " + (result1 && !(result2 || result3)));
    }

    boolean crypt(String[] words, char[][] solution) {
        Map<Character, Long> solutionMap = parseSolution(solution);
        long num1 = decrypt(solutionMap, words[0]);
        if (num1 == -1) {
            return false;
        }
        long num2 = decrypt(solutionMap, words[1]);
        if (num2 == -1) {
            return false;
        }
        long sum = decrypt(solutionMap, words[2]);
        if (sum == -1) {
            return false;
        }
        return num1 + num2 == sum;
    }

    long decrypt(Map<Character, Long> solution, String word) {
        boolean leadingZero = false;
        long value = 0;
        int pow = word.length() - 1;
        for (int i = 0; i < word.length(); ++i, --pow) {
            if (leadingZero) {
                return -1;
            }
            long digit = solution.get(word.charAt(i));
            if (i == 0 && digit == 0) {
                leadingZero = true;
            }
            value += digit * Math.pow(10, pow);
        }
        return value;
    }

    Map<Character, Long> parseSolution(char[][] solution) {
        Map<Character, Long> result = new HashMap<>();
        for (char[] cols : solution) {
            result.put(cols[0], (long) (cols[1] - '0'));
        }
        return result;
    }
}