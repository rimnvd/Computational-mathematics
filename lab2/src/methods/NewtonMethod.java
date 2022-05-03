package methods;

import data.EquationSystem;
import exceptions.IncorrectDataException;

public class NewtonMethod {
    private final EquationSystem equationSystem;
    private final utils.ioHandler ioHandler;

    public NewtonMethod(EquationSystem equationSystem, utils.ioHandler ioHandler) {
        this.equationSystem = equationSystem;
        this.ioHandler = ioHandler;
    }

    private double[][] createJacobianMatrix(double[][] partialDerivatives) {
        double[][] jacobian = new double[partialDerivatives.length][partialDerivatives.length];
        for (int i = 0; i < jacobian.length; i++) {
            System.arraycopy(partialDerivatives[i], 0, jacobian[i], 0, partialDerivatives.length);
        }
        return jacobian;
    }

    public double[] calcRoots() throws IncorrectDataException {
        int countIteration = 1;
        double epsilon = equationSystem.getEpsilon();
        while (true) {
        double[][] matrix = createJacobianMatrix(equationSystem.calcPartialDerivatives());
        double[] freeTerms = createFreeTerms(equationSystem.calcEquations());
        double[] currentApproximation = new double[equationSystem.getCurrentApproximation().length];
        System.arraycopy(equationSystem.getCurrentApproximation(), 0, currentApproximation, 0, equationSystem.getCurrentApproximation().length);
        GaussMethod gaussMethod = new GaussMethod();
            double[] increment = gaussMethod.doMethod(matrix, freeTerms);
            double[] nextApproximation = createNextApproximation(currentApproximation, increment);
            boolean flag = true;
            double[] difference = new double[nextApproximation.length];
            for (int i = 0; i < nextApproximation.length; i++) {
                difference[i] = Math.abs(nextApproximation[i] - currentApproximation[i]);
                if (Math.abs(nextApproximation[i] - currentApproximation[i]) > epsilon) {
                    flag = false;
                }
            }
            ioHandler.printSystemIteration(countIteration, nextApproximation, difference);
            if (flag) return nextApproximation;
            else {
                equationSystem.setCurrentApproximation(nextApproximation);
                countIteration++;
            }
        }
    }

    private double[] createFreeTerms(double[] solutions) {
        double[] freeTerms = new double[solutions.length];
        for (int i = 0; i < solutions.length; i++) {
            freeTerms[i] = (-1) * solutions[i];
        }
        return freeTerms;
    }

    private double[] createNextApproximation(double[] currentApproximation, double[] increment) {
        double[] nextApproximation = new double[currentApproximation.length];
        for (int i = 0; i < currentApproximation.length; i++) {
            nextApproximation[i] = currentApproximation[i] + increment[i];
        }
        return nextApproximation;
    }







}
