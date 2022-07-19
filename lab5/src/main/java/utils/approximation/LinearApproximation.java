package utils.approximation;

import data.Equation;

public class LinearApproximation extends Approximation {

    public LinearApproximation(Equation equation, int type) {
        super(equation, type);
    }

    @Override
    public void doApproximation() {
        coefficients = doApproximation1(xList, yList);
    }

    @Override
    public Double getFunction(double x) {
        return coefficients[0] * x + coefficients[1];
    }

}
