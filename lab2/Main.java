import data.Equation;
import data.EquationSystem;
import exceptions.IncorrectDataException;
import methods.BisectionMethod;
import methods.NewtonMethod;
import methods.TangentMethod;
import utils.ioHandler;

public class Main {
    public static void main(String[] args) {
        Equation equation = new Equation();
        EquationSystem equationSystem = new EquationSystem();
        ioHandler ioHandler = new ioHandler(equation, equationSystem);
        ioHandler.readInformationForEquation();
        BisectionMethod bisectionMethod = new BisectionMethod(equation);
        try {
            TangentMethod tangentMethod = new TangentMethod(equation);
            double root1 = bisectionMethod.calcRoot();
            double root2 = tangentMethod.calcRoot();
            ioHandler.printEquationRoot(root1, root2);
            ioHandler.printDifference(root1, root2);
        } catch (IncorrectDataException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
        ioHandler.readInformationForEquationSystem();
        NewtonMethod newtonMethod = new NewtonMethod(equationSystem, ioHandler);
        try {
            double[] roots = newtonMethod.calcRoots();
            ioHandler.printEquationSystemRoots(roots);
        } catch (IncorrectDataException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }


    }
}
