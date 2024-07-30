public class Simplex {
    private double[][] tableau;
    private int numVariables;
    private int numConstraints;

    public Simplex(double[][] tableau) {
        this.tableau = tableau;
        this.numVariables = tableau[0].length - 1;
        this.numConstraints = tableau.length - 1;
    }

    public void solveMaximization() {
        while (true) {
            int pivotColumn = findPivotColumn();
            if (pivotColumn == -1) break;
            int pivotRow = findPivotRow(pivotColumn);
            if (pivotRow == -1) throw new RuntimeException("Unbounded solution.");
            pivot(pivotRow, pivotColumn);
        }
    }

    public void solveMinimization() {
        // Negate the objective function for minimization
        for (int i = 0; i < numVariables; i++) {
            tableau[0][i] = -tableau[0][i];
        }
        solveMaximization();
        // Re-negate the objective function to get the correct result
        for (int i = 0; i < numVariables; i++) {
            tableau[0][i] = -tableau[0][i];
        }
    }

    private int findPivotColumn() {
        int pivotColumn = -1;
        double min = 0;
        for (int j = 1; j < tableau[0].length - 1; j++) {
            if (tableau[0][j] < min) {
                min = tableau[0][j];
                pivotColumn = j;
            }
        }
        return pivotColumn;
    }

    private int findPivotRow(int pivotColumn) {
        int pivotRow = -1;
        double minRatio = Double.MAX_VALUE;
        for (int i = 1; i < tableau.length; i++) {
            double ratio = tableau[i][tableau[0].length - 1] / tableau[i][pivotColumn];
            if (tableau[i][pivotColumn] > 0 && ratio < minRatio) {
                minRatio = ratio;
                pivotRow = i;
            }
        }
        return pivotRow;
    }

    private void pivot(int pivotRow, int pivotColumn) {
        double pivotElement = tableau[pivotRow][pivotColumn];
        for (int j = 0; j < tableau[0].length; j++) {
            tableau[pivotRow][j] /= pivotElement;
        }
        for (int i = 0; i < tableau.length; i++) {
            if (i != pivotRow) {
                double factor = tableau[i][pivotColumn];
                for (int j = 0; j < tableau[0].length; j++) {
                    tableau[i][j] -= factor * tableau[pivotRow][j];
                }
            }
        }
    }

    public void printTableau() {
        for (double[] row : tableau) {
            for (double value : row) {
                System.out.printf("%8.2f ", value);
            }
            System.out.println();
        }
    }

    public void printOptimalSolution() {
        System.out.println("Solução ótima:");
        for (int i = 1; i <= numVariables; i++) {
            double value = 0;
            for (int j = 1; j < tableau.length; j++) {
                if (tableau[j][i] == 1) {
                    value = tableau[j][tableau[0].length - 1];
                    break;
                }
            }
            System.out.printf("Variável x%d: %.2f%n", i, value);
        }
        System.out.printf("Valor da função objetivo: %.2f%n", tableau[0][tableau[0].length - 1]);
    }
}
