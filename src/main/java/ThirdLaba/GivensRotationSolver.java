package ThirdLaba;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;

public class GivensRotationSolver {

    public static void main(String[] args) {

        double[][] coefficients = {
                {1.08, 0.996},
                {0.991, 0.944}
        };

        double[] rhs = {0.502, 0.482};

        RealMatrix A = MatrixUtils.createRealMatrix(coefficients);
        RealVector b = new ArrayRealVector(rhs);

        RealMatrix R = A.copy();
        RealVector newB = b.copy();
        applyGivensRotation(R, newB);

        RealVector solution = backSubstitution(R, newB);

        System.out.println("x1 = " + solution.getEntry(0));
        System.out.println("x2 = " + solution.getEntry(1));
    }

    private static void applyGivensRotation(RealMatrix R, RealVector b) {
        int n = R.getRowDimension();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double a = R.getEntry(i, i);
                double bVal = R.getEntry(j, i);
                double r = Math.sqrt(a * a + bVal * bVal);
                double cos = a / r;
                double sin = -bVal / r;
                for (int k = 0; k < R.getColumnDimension(); k++) {
                    double temp = cos * R.getEntry(i, k) - sin * R.getEntry(j, k);
                    R.setEntry(j, k, sin * R.getEntry(i, k) + cos * R.getEntry(j, k));
                    R.setEntry(i, k, temp);
                }
                double tempB = cos * b.getEntry(i) - sin * b.getEntry(j);
                b.setEntry(j, sin * b.getEntry(i) + cos * b.getEntry(j));
                b.setEntry(i, tempB);
            }
        }
    }
    private static RealVector backSubstitution(RealMatrix R, RealVector b) {
        int n = R.getRowDimension();
        RealVector x = new ArrayRealVector(n);
        for (int i = n - 1; i >= 0; i--) {
            double sum = b.getEntry(i);
            for (int j = i + 1; j < n; j++) {
                sum -= R.getEntry(i, j) * x.getEntry(j);
            }
            x.setEntry(i, sum / R.getEntry(i, i));
        }
        return x;
    }
}
