package approximation;

import data.Function;

public class LogarithmicApproximation extends Approximation {

    public LogarithmicApproximation(Function function) {
        super(function);
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
