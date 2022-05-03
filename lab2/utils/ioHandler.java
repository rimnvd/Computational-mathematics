package utils;

import data.Equation;
import data.EquationSystem;

import java.util.Scanner;

public class ioHandler {
    Scanner scanner = new Scanner(System.in);
    Equation equation;
    EquationSystem equationSystem;

    public ioHandler(Equation equation, EquationSystem equationSystem) {
        this.equation = equation;
        this.equationSystem = equationSystem;
    }

    public void readInformationForEquation() {
        equation.setEquationNumber(chooseEquation());
        System.out.println();
        equation.setBounds(readInterval());
        System.out.println();
        equation.setEpsilon(readEpsilon());
        System.out.println();
    }

    public void readInformationForEquationSystem() {
        equationSystem.setEquationNumber(chooseEquationSystem());
        System.out.println();
        equationSystem.setCurrentApproximation(readRoots());
        System.out.println();
        equationSystem.setEpsilon(readEpsilon());
        System.out.println();

    }

    private int chooseEquation() {
        System.out.println("Выберите уравнение из списка. Введите число от 1 до 3");
        System.out.println("1. 5x^3 - x^2 - 20x + 4 = 0");
        System.out.println("2. sinx * lnx + x = 0");
        System.out.println("3. x * lnx * lgx -2 = 0");
        return readInt();
    }

    private int chooseEquationSystem() {
        System.out.println("Выберите систему уравнений из списка. Введите число от 1 до 3");
        System.out.println("1. x^2 + y^2 = 25");
        System.out.println("   y = -4x^4");
        System.out.println();
        System.out.println("2. sin(x - 0.6) -y = 1.6");
        System.out.println("   3 * x - cosy = 0.9");
        System.out.println();
        System.out.println("3. 2 * x^2 + y^2 - 4 * z^2 = 0");
        System.out.println("   x^2 + y^2 + z^2 = 1");
        System.out.println("   3 * x^2 - 4 * y + z^2 = 0");
        return readInt();
    }

    private int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= 3) {
                    return number;
                } else {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    private double[] readInterval() {
        System.out.println("Введите границы интервала через пробел");
        return readDoubleArray(2);
    }

    private double[] readRoots() {
        System.out.print("Введите начальное приближение. ");
        if (equationSystem.getEquationNumber() == 1 || equationSystem.getEquationNumber() == 2) {
            System.out.println("Необходимо ввести 2 корня через пробел");
            return readDoubleArray(2);
        } else {
            System.out.println("Необходимо ввести 3 корня через пробел");
            return readDoubleArray(3);
        }
    }

    private double[] readDoubleArray(int len) {
        double[] array = new double[len];
        while (true) {
            String[] input = scanner.nextLine().trim().split(" ");
            if (input.length != len) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            } else {
                try {
                    for (int i = 0; i < len; i++) {
                        array[i] = Double.parseDouble(input[i]);
                    }
                    return array;
                } catch (NumberFormatException ex) {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            }
        }
    }

    private double readEpsilon() {
        System.out.println("Введите точность");
        while (true) {
            String input = scanner.nextLine();
            try {
                double number = Double.parseDouble(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    public void printEquationRoot(double answer1, double answer2) {
        System.out.println("Ответ, полученный с помощью метода деления пополам: " + answer1);
        System.out.println("Ответ, полученный с помощью метода касательных: " + answer2);
        System.out.println();
    }

    public void printEquationSystemRoots(double[] roots) {
        char[] letters = {'x', 'y', 'z'};
        System.out.println("Ответ:");
        for (int i = 0; i < roots.length; i++) {
            System.out.println("\t" + letters[i] + " = " + roots[i]);
        }
    }

    public void printDifference(double answer1, double answer2) {
        System.out.println("Разница: " + Math.abs(answer1 - answer2));
        System.out.println();
    }

    public void printSystemIteration(int count, double[] roots, double[] difference) {
        char[] letters = {'x', 'y', 'z'};
        System.out.println("Итерация " + count + ":");
        for(int i = 0; i < roots.length; i++) {
            System.out.println("\t" + letters[i] + " = " + roots[i] + " epsilon = " + difference[i]);
        }
        System.out.println();
    }

}
