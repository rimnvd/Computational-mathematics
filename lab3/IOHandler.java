import java.util.Scanner;

public class IOHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final Data data;

    public IOHandler(Data data) {
        this.data = data;
    }

    public void input() {
        data.setFunctionNumber(chooseFunction());
        System.out.println();
        double[] integrationLimits = readLimits();
        data.setLowerLimit(integrationLimits[0]);
        data.setUpperLimit(integrationLimits[1]);
        System.out.println();
        data.setEpsilon(readEpsilon());
        System.out.println();
    }

    private int chooseFunction() {
        System.out.println("Выберите функцию из списка. Введите число от 1 до 4");
        System.out.println("1. y = x^3 - 2x + 3");
        System.out.println("2. y = sinx / x");
        System.out.println("3. y = 3lnx / (x - 1)");
        System.out.println("4. y = xcosx");
        return readInt();
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

    private double readDouble() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    private double[] readLimits() {
        while (true) {
            System.out.println("Введите нижний предел интегрирования");
            double lowerLimit = readDouble();
            System.out.println();
            System.out.println("Введите верхний предел интегрирования");
            double upperLimit = readDouble();
            if (lowerLimit > upperLimit) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                System.out.println();
            } else {
                return new double[] {lowerLimit, upperLimit};
            }
        }
    }

    private double readEpsilon() {
        System.out.println("Введите точность");
        while (true) {
            double epsilon = readDouble();
            if (epsilon > 0) return epsilon;
        }
    }


}
