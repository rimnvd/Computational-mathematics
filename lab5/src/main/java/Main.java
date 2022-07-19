import data.Equation;
import utils.IOHandler;
import utils.Plot;
import utils.approximation.ApproximationUtil;

public class Main {
    public static void main(String[] args) {
        Equation equation = new Equation();
        IOHandler ioHandler = new IOHandler(equation);
        ioHandler.input();
        new MilneMethod(equation).doMilneMethod();
        ioHandler.printPoints();
        ApproximationUtil approximationUtil = new ApproximationUtil(equation, ioHandler);
        approximationUtil.doApproximation();
        new Plot(equation).draw();

    }
}
