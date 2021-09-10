package experiments.problem.sudokuChecker;

import experiments.Experiment;
import experiments.problem.Utils;

public class SudokuCheckerExperiment extends Experiment {

    private static final int MATRIX_SIZE = 9;
    private static final char EMPTY_CELL = '.';

    @Override
    protected void execute() {
        char[][] input = {
            {'.', '.', '.', '1', '4', '.', '.', '2', '.'},
            {'.', '.', '6', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '1', '.', '.', '.', '.', '.', '.'},
            {'.', '6', '7', '.', '.', '.', '.', '.', '9'},
            {'.', '.', '.', '.', '.', '.', '8', '1', '.'},
            {'.', '3', '.', '.', '.', '.', '.', '.', '6'},
            {'.', '.', '.', '.', '.', '7', '.', '.', '.'},
            {'.', '.', '.', '5', '.', '.', '.', '7', '.'}
        };
        Utils.printMatrix(input);
        System.out.println("Sudoku checker works correct: " + sudoku2(input));
    }

    boolean sudoku2(char[][] grid) {
        for (int i = 0; i < MATRIX_SIZE; ++i) {
            for (int j = 0; j < MATRIX_SIZE; ++j) {
                char value = grid[i][j];
                if (value == EMPTY_CELL) {
                    continue;
                }
                if (!(checkRow(grid, i, value)
                    && checkColumn(grid, j, value)
                    && check3X3Grid(grid, i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkRow(char[][] grid, int row, int value) {
        int count = 0;
        for (int j = 0; j < MATRIX_SIZE; ++j) {
            if (grid[row][j] == value) {
                ++count;
            }
        }
        return count == 1;
    }

    boolean checkColumn(char[][] grid, int column, int value) {
        int count = 0;
        for (int i = 0; i < MATRIX_SIZE; ++i) {
            if (grid[i][column] == value) {
                ++count;
            }
        }
        return count == 1;
    }

    boolean check3X3Grid(char[][] grid, int i, int j) {
        int count = 0;
        char value = grid[i][j];
        int gridIStart = i / 3 * 3;
        int gridJStart = j / 3 * 3;
        for (int gridI = gridIStart; gridI < gridIStart + 3; ++gridI) {
            for (int gridJ = gridJStart; gridJ < gridJStart + 3; ++gridJ) {
                if (grid[gridI][gridJ] == value) {
                    ++count;
                }
            }
        }
        return count == 1;
    }
}
