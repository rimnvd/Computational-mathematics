package data;

import lombok.Data;

import java.util.*;

@Data
public class Function {
    private Map<Integer, List<Point>> functions;
    private int functionNumber;
    private ArrayList<Double> xList;
    private ArrayList<Double> yList;
    private ArrayList<Double> lnxList;
    private ArrayList<Double> lnyList;

    public Function() {
        functions = new HashMap<>();
        functions.put(1, Arrays.asList(
                new Point(1.2, 7.4),
                new Point(2.9, 9.5),
                new Point(4.1, 11.1),
                new Point(5.5, 12.9),
                new Point(6.7, 14.6),
                new Point(7.8, 17.3),
                new Point(9.2, 18.2),
                new Point(10.3, 20.7)
        ));
        functions.put(2, Arrays.asList(
                new Point(0.1, 8.3),
                new Point(0.4, 6.8),
                new Point(0.5, 5.2),
                new Point(0.8, 3.6),
                new Point(1, 2),
                new Point(1.8, 0.6),
                new Point(2.9, 0.4),
                new Point(3.8, 0.2)
        ));
        functions.put(3, Arrays.asList(
                new Point(1, 1.15),
                new Point(1.1, 1.6),
                new Point(1.2, 1.79),
                new Point(1.3, 2.29),
                new Point(1.4, 2.6),
                new Point(1.5, 3.13),
                new Point(1.6, 3.5),
                new Point(1.7, 4.18)
        ));
        functions.put(4, Arrays.asList(
                new Point(1, 2),
                new Point(2, 6),
                new Point(3, 7),
                new Point(4, 13),
                new Point(5, 26),
                new Point(6, 42),
                new Point(7, 64),
                new Point(8, 110)
        ));
    }

    public void createLists(int functionNumber) {
        createListOfX(functionNumber);
        createListOfY(functionNumber);
        createListOfLnX(functionNumber);
        createListOfLnY(functionNumber);
    }

    public void createListOfX(int functionNumber) {
        xList = new ArrayList<>();
        List<Point> points = functions.get(functionNumber);
        for (Point point : points) {
            xList.add(point.getX());
        }
    }

    public void createListOfY(int functionNumber) {
        yList = new ArrayList<>();
        List<Point> points = functions.get(functionNumber);
        for (Point point : points) {
            yList.add(point.getY());
        }
    }

    public void createListOfLnX(int functionNumber) {
        lnxList = new ArrayList<>();
        List<Point> points = functions.get(functionNumber);
        for (Point point : points) {
            lnxList.add(Math.log(point.getX()));
        }
    }

    public void createListOfLnY(int functionNumber) {
        lnyList = new ArrayList<>();
        List<Point> points = functions.get(functionNumber);
        for (Point point : points) {
            lnyList.add(Math.log(point.getY()));
        }
    }

    public void addNewFunction(List<Point> points) {
        functions.put(functions.size() + 1, points);
    }

    public List<Point> getFunction() {
        return functions.get(functionNumber);
    }
}
