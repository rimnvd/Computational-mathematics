package utils.approximation;

import data.Equation;
import utils.IOHandler;

public class ApproximationUtil {
    private final Equation equation;
    private final IOHandler ioHandler;

    public ApproximationUtil(Equation equation, IOHandler ioHandler) {
        this.equation = equation;
        this.ioHandler = ioHandler;
    }

    public void doApproximation() {
        double standardDeviationForLinearFunction = new LinearApproximation(equation, 1).calcStandardDeviation();
        double standardDeviationForExponentialFunction  = new ExponentialApproximation(equation, 1).calcStandardDeviation();
        double standardDeviationForLogarithmicFunction = new LogarithmicApproximation(equation, 1).calcStandardDeviation();
        double standardDeviationForPowerFunction = new PowerApproximation(equation, 1).calcStandardDeviation();
        Approximation approximation;
        if (standardDeviationForLinearFunction < standardDeviationForExponentialFunction &&
                standardDeviationForLinearFunction < standardDeviationForLogarithmicFunction &&
                standardDeviationForLinearFunction < standardDeviationForPowerFunction) {
            approximation = new LinearApproximation(equation, 1);
            ioHandler.printTypeOfFunction("линейная");
        } else if (standardDeviationForExponentialFunction < standardDeviationForLogarithmicFunction &&
                standardDeviationForExponentialFunction < standardDeviationForPowerFunction) {
            approximation = new ExponentialApproximation(equation, 1);
            ioHandler.printTypeOfFunction("экспоненциальная");
        } else if (standardDeviationForLogarithmicFunction < standardDeviationForPowerFunction) {
            approximation = new LogarithmicApproximation(equation, 1);
            ioHandler.printTypeOfFunction("логарифмическая");
        } else {
            approximation = new PowerApproximation(equation, 1);
            ioHandler.printTypeOfFunction("степенная");
        }
        approximation.doApproximation();
        equation.getPointsForChart().put(1, approximation.createFunctionForChart());
        equation.fillAnalyticSolveList();
        approximation.changeType(2);
        approximation.doApproximation();
        equation.getPointsForChart().put(2, approximation.createFunctionForChart());
    }
}
