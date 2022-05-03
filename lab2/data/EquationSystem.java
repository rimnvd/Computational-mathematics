package data;

public class EquationSystem {

    private int equationNumber;
    private double[] currentApproximation;
    private double epsilon;

    public int getEquationNumber() {
        return equationNumber;
    }

    public void setEquationNumber(int equationNumber) {
        this.equationNumber = equationNumber;
    }

    public double[] getCurrentApproximation() {
        return currentApproximation;
    }

    public void setCurrentApproximation(double[] currentApproximation) {
        this.currentApproximation = currentApproximation;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public double[] calcEquations() {
        double x = currentApproximation[0];
        double y = currentApproximation[1];
        double z;
        if (currentApproximation.length == 2) {
            z = 0;
        } else {
            z = currentApproximation[2];
        }
        switch (equationNumber) {
            case 1:
                return new double[] {Math.pow(x, 2) + Math.pow(y, 2) - 25, 4 * Math.pow(x, 4) + y};
            case 2:
                return new double[] {Math.sin(x - 0.6) - y - 1.6, 3 * x - Math.cos(y) - 0.9};
            default:
                return new double[] {2 * x * x + y * y - 4 * z, x * x + y * y + z * z - 1, 3 * x * x - 4 * y + z * z};
        }
    }

    public double[][] calcPartialDerivatives() {
        double x = currentApproximation[0];
        double y = currentApproximation[1];
        double z;
        if (currentApproximation.length == 2) {
            z = 0;
        } else {
            z = currentApproximation[2];
        }
        switch (equationNumber) {
            case 1:
                return new double[][] {{2 * x, 2 * y}, {16 * Math.pow(x, 3), 1}};
            case 2:
                return new double[][] {{Math.cos(x - 0.6), -1}, {3, Math.sin(x)}};
            default:
                return new double[][] {{4 * x, 2 * y, -4}, {2 * x, 2 * y, 2 * z}, {6 * x, -4, 2 * z}};
        }
    }
}
