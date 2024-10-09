package SecondLaba;

import java.util.Arrays;
public class GaussZeidelMethod {
    private static final double EPSILON = 0.01;

    public static void main(String[] args) {

        double[][] A = {
                {6.9000, 0.0319, 0.0390, 0.0461},
                {0.0191, 6.0000, 0.0333, 0.0405},
                {0.0134, 0.0149, 5.1000, 0.0348},
                {0.0077, 0.0205, 0.0220, 4.2000}
        };
        double[] b = {5.6632, 6.1119, 6.2000, 5.9275};

        double[] x = new double[b.length];
        Arrays.fill(x, 0);

        gaussSeidelMethod(A, b, x);
    }

    public static void gaussSeidelMethod(double[][] A, double[] b, double[] x) {
        int n = b.length;
        boolean convergence;

        int iteration = 0;
        do {
            convergence = true;
            for (int i = 0; i < n; i++) {
                double oldXi = x[i];
                double sum = b[i];

                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= A[i][j] * x[j];
                    }
                }
                x[i] = sum / A[i][i];

                if (Math.abs(x[i] - oldXi) > EPSILON) {
                    convergence = false;
                }
            }

            iteration++;

            System.out.println("Итерация " + iteration + ": " + Arrays.toString(x));
        } while (!convergence);

        System.out.println("Решение достигнуто за " + iteration + " итераций.");
        System.out.println("Результат: " + Arrays.toString(x));
    }
}
