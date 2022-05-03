public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        IOHandler ioHandler = new IOHandler(data);
        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod(data);
        ioHandler.input();
        try {
            data.isFunctionDefined();
            System.out.println(trapezoidalMethod.doMethod());
        } catch (FunctionNotDefinedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
