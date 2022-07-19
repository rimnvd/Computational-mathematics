package approximation;

import data.Function;


public class ExponentialApproximation extends Approximation {

    public ExponentialApproximation(Function function) {
        super(function);
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
