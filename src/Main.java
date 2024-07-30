public class Main {
    public static void main(String[] args) {
        double[][] tableauMax = SimplexSolver.createMaximizationTableau();
        Simplex simplexMax = new Simplex(tableauMax);
        simplexMax.solveMaximization();
        System.out.println("Resultado Maximização:");
        simplexMax.printTableau();
        simplexMax.printOptimalSolution();

        double[][] tableauMin = SimplexSolver.createMinimizationTableau();
        Simplex simplexMin = new Simplex(tableauMin);
        simplexMin.solveMinimization();
        System.out.println("Resultado Minimização:");
        simplexMin.printTableau();
        simplexMin.printOptimalSolution();
    }
}
