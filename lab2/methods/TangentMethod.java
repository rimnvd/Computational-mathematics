package methods;

import data.Equation;
import exceptions.IncorrectDataException;

public class TangentMethod {
    private final Equation equation;
    double root;
    private final double epsilon;

    public TangentMethod(Equation equation) throws IncorrectDataException {
        this.equation = equation;
        this.epsilon = equation.getEpsilon();
        setRoot();
    }

    public double calcRoot() {
        double newRoot = root - equation.calcEquation(root) / equation.calcFirstDerivative(root);
        if (Math.abs(newRoot - root) <= epsilon || Math.abs(equation.calcEquation(newRoot)) <= epsilon) return newRoot;
        else {
            root = newRoot;
            return calcRoot();
        }
    }

    public void setRoot() throws IncorrectDataException {
        if (equation.calcEquation(equation.getBounds()[0]) * equation.calcSecondDerivative(equation.getBounds()[0]) > 0) {
            root = equation.getBounds()[0];
        } else if (equation.calcEquation(equation.getBounds()[1]) * equation.calcSecondDerivative(equation.getBounds()[1]) > 0) {
            root = equation.getBounds()[1];
        } else {
            throw new IncorrectDataException("Невозможно выбрать начальное приближение");
        }
    }
}
