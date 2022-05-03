
package methods;

import data.Equation;
import exceptions.IncorrectDataException;

public class BisectionMethod {
    private final Equation equation;
    private double leftBound;
    private double rightBound;
    private final double epsilon;

    public BisectionMethod(Equation equation) {
        this.equation = equation;
        this.leftBound = equation.getBounds()[0];
        this.rightBound = equation.getBounds()[1];
        this.epsilon = equation.getEpsilon();
    }

    private boolean checkNecessity() {
        return equation.calcEquation(leftBound) * equation.calcEquation(rightBound) < 0;
    }

    public double calcRoot() throws IncorrectDataException {
        if (checkNecessity()) {
            while (Math.abs(rightBound - leftBound) > epsilon) {
                double root = (leftBound + rightBound) / 2;
                if (equation.calcEquation(leftBound) * equation.calcEquation(root) < 0) {
                    rightBound = root;
                } else {
                    leftBound = root;
                }
            }
            return (leftBound + rightBound) / 2;
        } else {
            throw new IncorrectDataException("Необходимое условие существования корня на заданном отрезке не выполнено");
        }
    }
}
