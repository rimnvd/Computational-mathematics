package approximation;

import data.Function;

public class PowerApproximation extends Approximation {

    public PowerApproximation(Function function) {
        super(function);
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
