package src.FirstLaba;

public class CholeskyDecomposition {

    public static void main(String[] args) {
        // Исходная матрица
        double[][] A = {
                {2.36, 2.37, 2.13},
                {2.51, 2.40, 2.10},
                {2.59, 2.41, 2.06}
        };

        // Вектор
        double[] b = {1.48, 1.92, 2.16};

        // Сделаем матрицу положительно определенной
        makePositiveDefinite(A);

        double[] solution = choleskySolve(A, b);

        // Вывод результата
        System.out.println("Решение системы уравнений (x):");
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("x%d = %.16f%n", (i + 1), solution[i]);
        }

        // Проверка результата A * x
        double[] Ax = new double[b.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                Ax[i] += A[i][j] * solution[j];
            }
        }

        // Вывод результата A * x
        System.out.println("\nРезультат A * x:");
        for (int i = 0; i < Ax.length; i++) {
            System.out.printf("Ax[%d] = %.16f%n", i, Ax[i]);
        }
    }

    // Метод для приведения матрицы к положительно определенной
    private static void makePositiveDefinite(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i][i] <= 0) {
                A[i][i] += 0.1; // Увеличиваем диагональные элементы
            }
        }
    }

    public static double[] choleskySolve(double[][] A, double[] b) {
        int n = A.length;
        double[][] L = new double[n][n];

        // Разложение Холецкого
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * L[j][k];
                }
                if (i == j) {
                    double value = A[i][i] - sum;
                    if (value <= 0) {
                        throw new RuntimeException("Матрица не является положительно определенной.");
                    }
                    L[i][i] = Math.sqrt(value);
                } else {
                    L[i][j] = (A[i][j] - sum) / L[j][j];
                }
            }
        }

        // Прямой ход для решения Ly = b
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int k = 0; k < i; k++) {
                sum += L[i][k] * y[k];
            }
            y[i] = (b[i] - sum) / L[i][i];
        }

        // Обратный ход для решения L^T x = y
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int k = i + 1; k < n; k++) {
                sum += L[k][i] * x[k];
            }
            x[i] = (y[i] - sum) / L[i][i];
        }

        return x;
    }
}
