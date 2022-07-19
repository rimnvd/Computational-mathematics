package utils.approximation;

import data.Equation;

public class PowerApproximation extends Approximation {

    public PowerApproximation(Equation equation, int type) {
        super(equation, type);
    }

    @Override
    public void doApproximation() {
        coefficients = doApproximation2(lnxList, lnyList);
    }

    @Override
    public Double getFunction(double x) {
        return coefficients[0] * Math.pow(x, coefficients[1]);
    }

}
