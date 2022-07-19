package utils.approximation;

import data.Equation;

public class LogarithmicApproximation extends Approximation {

    public LogarithmicApproximation(Equation equation, int type) {
        super(equation, type);
    }

    @Override
    public void doApproximation() {
        coefficients = doApproximation1(lnxList, yList);
    }

    @Override
    public Double getFunction(double x) {
        return coefficients[0] * Math.log(x) + coefficients[1];
    }
}
