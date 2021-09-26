package experiments.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    private Utils() {
    }

    public static List<Integer> getNewPositiveList() {
        return new ArrayList<>(Arrays.asList(5, 8, 6, 8, 0, 8, 10, 0, 8, 5, 15, 10));
    }

    public static List<Integer> getNewMixedList() {
        return new ArrayList<>(Arrays.asList(-5, 8, 6, 8, 0, 8, -10, 0, -8, 5, 15, 10));
    }
}
