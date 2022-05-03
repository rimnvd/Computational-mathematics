public class IterativeMethod {
    private int countIterations;
    private double[] error;

    public boolean checkDiagonallyDominant(int dim, double[][] matrix, double[] freeTerms) {
        for (int i = 0; i < dim; i++) {
            double rowSum = 0;
            double maxInRow = 0;
            int indexOfMax = -1;
            for (int j = 0; j < dim; j++) {
                rowSum += Math.abs(matrix[i][j]);
                if (Math.abs(matrix[i][j]) > maxInRow) {
                    maxInRow = Math.abs(matrix[i][j]);
                    indexOfMax = j;
                }
            }
            if (maxInRow >= rowSum - maxInRow) {
                double[] tempMatrix = matrix[i];
                matrix[i] = matrix[indexOfMax];
                matrix[indexOfMax] = tempMatrix;
                double tempFreeTerm = freeTerms[i];
                freeTerms[i] = freeTerms[indexOfMax];
                freeTerms[indexOfMax] = tempFreeTerm;
            } else {
                return false;
            }
        }
        for (int i = 0; i < dim; i++) {
            double maxInRow = 0;
            int indexOfMax = -1;
            for (int j = 0; j < dim; j++) {
                if (matrix[i][j] > maxInRow) {
                    maxInRow = matrix[i][j];
                    indexOfMax = j;
                }
            }
            if (indexOfMax != i) {
                return false;
            }
        }
        return true;
    }

    public void normalize(int dim, double[][] matrix, double[] freeTerms) {
        for (int i = 0; i < dim; i++) {
            double coefficient = matrix[i][i];
            for (int j = 0; j < dim; j++) {
                matrix[i][j] = (-1) * matrix[i][j] / coefficient;
            }
            freeTerms[i] = freeTerms[i] / coefficient;
            matrix[i][i] = 0;
        }
    }

    public boolean checkConvergenceCondition(int dim, double[][] matrix) {
        double matrixNorm = 0;
        for (int i = 0; i < dim; i++) {
            double rowSum = 0;
            for (int j = 0; j < dim; j++) {
                rowSum += Math.abs(matrix[i][j]);
            }
            if (rowSum > matrixNorm) {
                matrixNorm = rowSum;
            }
        }
        return (matrixNorm < 1);
    }

    public double[] calcRoots(int dim, double[][] matrix, double[] freeTerms, double epsilon, double[] roots) {
        int countIteration = 0;
        while (true) {
            double[] previousRoots = roots.clone();
            for (int i = 0; i < dim; i++) {
                roots[i] = 0;
                for (int j = 0; j < dim; j++) {
                    if (i != j) {
                        roots[i] += matrix[i][j] * previousRoots[j];
                    }
                }
                roots[i] += freeTerms[i];
            }
            double resultEpsilon = -1;
            error = new double[roots.length];
            for (int i = 0; i < dim; i++) {
                error[i] = Math.abs(roots[i] - previousRoots[i]);
                if (Math.abs(roots[i] - previousRoots[i]) > resultEpsilon) {
                    resultEpsilon = Math.abs(roots[i] - previousRoots[i]);
                }
            }
            if (resultEpsilon <= epsilon) {
                countIteration++;
                this.countIterations = countIteration;
                return roots;
            } else {
                countIteration++;
            }
        }
    }

    public int getCountIterations() {
        return countIterations;
    }

    public double[] getError() {
        return error;
    }
}
