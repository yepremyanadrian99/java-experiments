package experiments.problem;

public class Utils {

    private Utils() {
    }

    public static void printMatrix(char[][] arr) {
        for (char[] cols : arr) {
            for (char value : cols) {
                System.out.print(value + "  ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(int[][] arr) {
        for (int[] cols : arr) {
            for (int value : cols) {
                System.out.print(value + "  ");
            }
            System.out.println();
        }
    }
}
