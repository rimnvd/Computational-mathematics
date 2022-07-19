package approximation;

import data.Function;

public class LinearApproximation extends Approximation {

    public LinearApproximation(Function function) {
        super(function);
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
