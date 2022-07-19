package utils.approximation;

import data.Equation;

public class ExponentialApproximation extends Approximation {

    public ExponentialApproximation(Equation equation, int type) {
        super(equation, type);
    }

    @Override
    public void doApproximation() {
        coefficients = doApproximation2(xList, lnyList);
    }

    @Override
    public Double getFunction(double x) {
        return coefficients[0] * Math.pow(Math.E, coefficients[1] * x);
    }
}
