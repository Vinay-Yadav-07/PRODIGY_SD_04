import java.util.Scanner;

public class Sudoku {
    private static final int SIZE = 9;
    private int[][] grid;

    public Sudoku(int[][] grid) {
        this.grid = grid;
    }

    public boolean solve() {
        int row, col;
        int[] emptyCell = findEmptyCell();
        row = emptyCell[0];
        col = emptyCell[1];

        if (row == -1 && col == -1) return true;

        for (int num = 1; num <= SIZE; num++) {
            if (isValidMove(row, col, num)) {
                grid[row][col] = num;

                if (solve()) return true;

                grid[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isValidMove(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) return false;
        }
        int subgridRowStart = row - row % 3, subgridColStart = col - col % 3;
        for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
            for (int j = subgridColStart; j < subgridColStart + 3; j++) {
                if (grid[i][j] == num) return false;
            }
        }
        return true;
    }

    private int[] findEmptyCell() {
        int[] result = new int[]{-1, -1};
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] unsolvedGrid = readInputGrid();
        Sudoku solver = new Sudoku(unsolvedGrid);
        if (solver.solve()) {
            System.out.println("Sudoku puzzle solved successfully:");
            solver.display();
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }

    private static int[][] readInputGrid() {
        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[SIZE][SIZE];
        System.out.println("Enter the unsolved Sudoku puzzle (use 0 to represent empty cells):");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        return grid;
    }
}