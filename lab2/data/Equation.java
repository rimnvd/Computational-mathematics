package data;

public class Equation {
    private int equationNumber;
    private double[] bounds;
    private double epsilon;

    public int getEquationNumber() {
        return equationNumber;
    }

    public void setEquationNumber(int equationNumber) {
        this.equationNumber = equationNumber;
    }

    public double[] getBounds() {
        return bounds;
    }

    public void setBounds(double[] bounds) {
        this.bounds = bounds;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public double calcEquation(double x) {
        switch (equationNumber) {
            case 1:
                return 5 * Math.pow(x, 3) - Math.pow(x, 2) - 20 * x + 4;
            case 2:
                return Math.sin(x) * Math.log(x) + x;
            default:
                return x * Math.log(x) * Math.log10(x) - 2;
        }
    }

    public double calcFirstDerivative(double x) {
        switch (equationNumber) {
            case 1:
                return 15 * Math.pow(x, 2) - 2 * x - 20;
            case 2:
                return Math.cos(x) * Math.log(x) + Math.sin(x) / x + 1;
            default:
                return Math.log(x) * (Math.log(x) + 2) / Math.log(10);
        }
    }

    public double calcSecondDerivative(double x) {
        switch (equationNumber) {
            case 1:
                return 30 * x - 2;
            case 2:
                return -Math.sin(x) * Math.log(x) + 2 * Math.cos(x) / x - Math.sin(x) / Math.pow(x, 2);
            default:
                return 2 * (Math.log(x) + 1) / (x * Math.log10(x));
        }
    }

    public double

}
