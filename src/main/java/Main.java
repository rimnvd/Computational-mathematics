import approximation.*;
import data.Function;
import utils.IOHandler;
import utils.Plot;

public class Main {
    public static void main(String[] args) {
        Function function = new Function();
        IOHandler ioHandler = new IOHandler(function);
        ioHandler.chooseFunction();
        function.createLists(function.getFunctionNumber());
        double standardDeviationForLinearFunction = new LinearApproximation(function).calcStandardDeviation();
        double standardDeviationForExponentialFunction  = new ExponentialApproximation(function).calcStandardDeviation();
        double standardDeviationForLogarithmicFunction = new LogarithmicApproximation(function).calcStandardDeviation();
        double standardDeviationForPowerFunction = new PowerApproximation(function).calcStandardDeviation();
        Approximation approximation;
        if (standardDeviationForLinearFunction < standardDeviationForExponentialFunction &&
                standardDeviationForLinearFunction < standardDeviationForLogarithmicFunction &&
                standardDeviationForLinearFunction < standardDeviationForPowerFunction) {
            approximation = new LinearApproximation(function);
            ioHandler.printTypeOfFunction("линейная");
        }

        else if (standardDeviationForExponentialFunction < standardDeviationForLogarithmicFunction &&
                standardDeviationForExponentialFunction < standardDeviationForPowerFunction) {
            approximation = new ExponentialApproximation(function);
            ioHandler.printTypeOfFunction("экспоненциальная");
        }

        else if (standardDeviationForLogarithmicFunction < standardDeviationForPowerFunction) {
            approximation = new LogarithmicApproximation(function);
            ioHandler.printTypeOfFunction("логарифмическая");
        }
        else {
            approximation = new PowerApproximation(function);
            ioHandler.printTypeOfFunction("степенная");
        }
        approximation.doApproximation();
        double[] coefficients = approximation.getCoefficients();
        ioHandler.printAnswer(coefficients[0], coefficients[1]);
        function.addNewFunction(approximation.createFunctionForChart());
        ioHandler.printInformationAboutExtraPoint();
        approximation.doSecondApproximation();
        coefficients = approximation.getCoefficients();
        ioHandler.printAnswer(coefficients[0], coefficients[1]);
        function.addNewFunction(approximation.createFunctionForChart());
        new Plot(function).draw();
    }
}
