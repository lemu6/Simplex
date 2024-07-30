public class SimplexSolver {
    public static double[][] createMaximizationTableau() {
        return new double[][]{
            { 0, -3, -5, 0, 0, 0, 0 },
            { 2,  1,  1, 1, 0, 0, 4 },
            { 1,  2,  2, 0, 1, 0, 6 },
            { 1,  1,  1, 0, 0, 1, 3 }
        };
    }

    public static double[][] createMinimizationTableau() {
        return new double[][]{
            { 0,  3,  5, 0, 0, 0, 0 },
            { 2,  1,  1, 1, 0, 0, 4 },
            { 1,  2,  2, 0, 1, 0, 6 },
            { 1,  1,  1, 0, 0, 1, 3 }
        };
    }
}
