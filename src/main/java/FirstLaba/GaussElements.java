package FirstLaba;

public class GaussElements {

    public static void main(String[] args) {
        double[][] hausElements = {
                {2.36, 2.37, 2.13, 1.48},
                {2.51, 2.40, 2.10, 1.92},
                {2.59, 2.41, 2.06, 2.16}
        };

        gaussElimination(hausElements);
    }

    public static void gaussElimination(double[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            double[] temp = matrix[i];
            matrix[i] = matrix[maxRow];
            matrix[maxRow] = temp;

            for (int k = i + 1; k < n; k++) {
                double factor = matrix[k][i] / matrix[i][i];
                for (int j = i; j <= n; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = matrix[i][n] / matrix[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matrix[k][n] -= matrix[k][i] * solution[i];
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
    }
}

