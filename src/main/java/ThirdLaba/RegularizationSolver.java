package ThirdLaba;

import org.apache.commons.math3.linear.*;

public class RegularizationSolver {

    public static void main(String[] args) {
        double[][] coefficients = {
                {1.08, 0.996},
                {0.991, 0.944}
        };

        double[] rhs = {0.502, 0.482};

        double lambda = 0.001;

        RealMatrix matrix = MatrixUtils.createRealMatrix(coefficients);
        RealVector vector = new ArrayRealVector(rhs);

        RealMatrix identity = MatrixUtils.createRealIdentityMatrix(matrix.getRowDimension());
        RealMatrix regularizedMatrix = matrix.add(identity.scalarMultiply(lambda));

        DecompositionSolver solver = new LUDecomposition(regularizedMatrix).getSolver();
        RealVector solution = solver.solve(vector);

        System.out.println("x1 = " + solution.getEntry(0));
        System.out.println("x2 = " + solution.getEntry(1));
    }
}
