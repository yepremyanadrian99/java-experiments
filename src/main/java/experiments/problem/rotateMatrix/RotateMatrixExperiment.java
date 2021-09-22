package experiments.problem.rotateMatrix;

import java.util.Arrays;

import experiments.Experiment;
import experiments.problem.Utils;

public class RotateMatrixExperiment extends Experiment {

    @Override
    protected void execute() {
        int[][] arr = {
            {10, 9, 6, 3, 7},
            {6, 10, 2, 9, 7},
            {7, 6, 3, 8, 2},
            {8, 9, 7, 9, 9},
            {6, 8, 6, 8, 2}
        };
        System.out.println("Before rotation");
        Utils.printMatrix(arr);
        rotateImage(arr);
        System.out.println("After rotation");
        Utils.printMatrix(arr);
        int[][] expected = {
            {6, 8, 7, 6, 10},
            {8, 9, 6, 10, 9},
            {6, 7, 3, 2, 6},
            {8, 9, 8, 9, 3},
            {2, 9, 2, 7, 7}
        };
        System.out.println("Matrix rotation works correctly: " + Arrays.deepEquals(arr, expected));
    }

    private void rotateImage(int[][] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int temp = move(arr, j, n - 1 - i, arr[i][j]);
                temp = move(arr, n - 1 - i, n - 1 - j, temp);
                temp = move(arr, n - 1 - j, i, temp);
                move(arr, i, j, temp);
            }
        }
    }

    private int move(int[][] arr, int i, int j, int data) {
        int temp = arr[i][j];
        arr[i][j] = data;
        return temp;
    }
}
