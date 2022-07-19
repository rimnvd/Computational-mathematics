package utils;

import data.Function;

import java.util.ArrayList;
import java.util.Scanner;

public class IOHandler {
    private final Function function;
    Scanner scanner = new Scanner(System.in);

    public IOHandler(Function function) {
        this.function = function;
    }

    public void chooseFunction() {
        System.out.println("Выберите функцию из списка. Введите число от 1 до 4");
        printPoints(1);
        System.out.println();
        printPoints(2);
        System.out.println();
        printPoints(3);
        System.out.println();
        printPoints(4);
        System.out.println();
        function.setFunctionNumber(readInt());
        System.out.println();
    }

    private void printPoints(int functionNumber) {
        function.createLists(functionNumber);
        System.out.println(functionNumber + ".");
        System.out.print("\tx | ");
        ArrayList<Double> xList = function.getXList();
        for (Double x : xList) {
            System.out.print(x + " | ");
        }
        System.out.println();
        System.out.print("\ty | ");
        ArrayList<Double> yList = function.getYList();
        for (Double y : yList) {
            System.out.print(y + " | ");
        }
        System.out.println();
    }

    private int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= 4) {
                    return number;
                } else {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    public void printAnswer(double a, double b) {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println();
    }

    public void printTypeOfFunction(String typeOfFunction) {
        System.out.println("Для аппроксимации будет использована " + typeOfFunction + " функция");
        System.out.println();
    }

    public void printInformationAboutExtraPoint() {
        System.out.println("Найдем точку с наибольшим отклонением от значения, полученного при помощи аппроксимирующей функции");
        System.out.println("Исключим данную точку и проведем пересчет коэффициентов");
        System.out.println();
    }

}
