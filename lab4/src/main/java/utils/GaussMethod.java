package utils;


public class GaussMethod {


    public double[][] toTriangularMatrix(double[][] matrix, double[] freeTerms) {
        int dim = matrix.length;
        double[][] newMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < dim; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
        }
        for (int i = 0; i < dim - 1; i++) {
            if (newMatrix[i][i] != 0) {
                for (int j = i + 1; j < dim; j++) {
                    if (newMatrix[j][i] != 0) {
                        double coefficient = newMatrix[j][i] / newMatrix[i][i];
                        for (int k = i; k < dim; k++) {
                            newMatrix[j][k] -= newMatrix[i][k] * coefficient;
                        }
                        freeTerms[j] -= freeTerms[i] * coefficient;
                    }
                }
            } else {
                int j = i + 1;
                while (matrix[j][i] == 0 && j < dim - 1) {
                    j++;
                }
                double[] tempRow = matrix[j];
                matrix[j] = matrix[i];
                matrix[i] = tempRow;
                double tempFreeTerm = freeTerms[j];
                freeTerms[j] = freeTerms[i];
                freeTerms[i] = tempFreeTerm;
            }
        }
        return newMatrix;
    }

    private double[] calcRoots(double[][] matrix, double[] freeTerms) {
        int dim = matrix.length;
        double[] roots = freeTerms.clone();
        for (int i = dim - 1; i > -1; i--) {
            for (int j = dim - 1; j > -1; j--) {
                if (j != i && matrix[i][j] != 0) {
                    roots[i] -= matrix[i][j] * roots[j];
                } else {
                    if (matrix[i][j] != 0) {
                        roots[i] = roots[i] / matrix[i][j];
                    }
                }
            }
        }
        return roots;
    }

    public double[] doMethod(double[][] matrix, double[] freeTerms) {
        double[] newFreeTerms = new double[freeTerms.length];
        System.arraycopy(freeTerms, 0, newFreeTerms, 0, freeTerms.length);
        double[][] triangularMatrix = toTriangularMatrix(matrix, newFreeTerms);
        return calcRoots(triangularMatrix, newFreeTerms);
    }
}
