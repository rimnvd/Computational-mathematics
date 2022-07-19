package approximation;

import data.*;
import utils.GaussMethod;

import java.util.ArrayList;
import java.util.List;


public class Approximation {

    protected final ArrayList<Double> xList;
    protected final ArrayList<Double> yList;
    protected final ArrayList<Double> lnxList;
    protected final ArrayList<Double> lnyList;
    protected double[] coefficients;


    public void doApproximation() {
    }

    public Approximation(Function function) {
        this.xList = function.getXList();
        this.yList = function.getYList();
        this.lnxList = function.getLnxList();
        this.lnyList = function.getLnyList();
    }

    protected double[] doApproximation1(ArrayList<Double> xList, ArrayList<Double> yList) {
        double xSum = getSumOfValues(xList);
        double ySum = getSumOfValues(yList);
        double xySum = getSumOfMultipliedValues(xList, yList);
        double squaredXSum = getSumOfSquaredValues(xList);
        return new GaussMethod().doMethod(new double[][]{{squaredXSum, xSum}, {xSum, xList.size()}}, new double[]{xySum, ySum});
    }

    protected double[] doApproximation2(ArrayList<Double> xList, ArrayList<Double> yList) {
        double xSum = getSumOfValues(xList);
        double ySum = getSumOfValues(yList);
        double xySum = getSumOfMultipliedValues(xList, yList);
        double squaredXSum = getSumOfSquaredValues(xList);
        double[] coefficients = new GaussMethod().doMethod(new double[][]{{xSum, squaredXSum}, {xList.size(), xSum}}, new double[]{xySum, ySum});
        coefficients[0] = Math.pow(Math.E, coefficients[0]);
        return coefficients;
    }

    public Double getFunction(double x) {
        return null;
    }

    private double getSumOfValues(ArrayList<Double> values) {
        double sum = 0.;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    private double getSumOfSquaredValues(ArrayList<Double> values) {
        double sum = 0.;
        for (double value : values) {
            sum += value * value;
        }
        return sum;
    }

    private double getSumOfMultipliedValues(ArrayList<Double> values1, ArrayList<Double> values2) {
        double sum = 0.;
        for (int i = 0; i < values1.size(); i++) {
            sum += values1.get(i) * values2.get(i);
        }
        return sum;
    }

    public Double calcStandardDeviation() {
        doApproximation();
        int n = xList.size();
        double standardDeviation = 0.;
        for (int i = 0; i < xList.size(); i++) {
            standardDeviation += Math.pow(getFunction(xList.get(i)) - yList.get(i), 2);
        }
        standardDeviation /= n;
        standardDeviation = Math.sqrt(standardDeviation);
        return standardDeviation;
    }

    private void returnExtraPoint(int index, Point point) {
        xList.add(index, point.getX());
        yList.add(index, point.getY());
        lnxList.add(index, Math.log(point.getX()));
        lnyList.add(index, Math.log(point.getY()));
    }

    private void deleteExtraPoint(int index) {
        xList.remove(index);
        yList.remove(index);
        lnxList.remove(index);
        lnyList.remove(index);
    }

    public void doSecondApproximation() {
        int index = findExtraPoint();
        Point point = new Point(xList.get(index), yList.get(index));
        deleteExtraPoint(index);
        doApproximation();
        returnExtraPoint(index, point);
    }

    private int findExtraPoint() {
        int index = -1;
        double maxDeviation = 0;
        for (int i = 0; i < xList.size(); i++) {
            if (Math.pow(getFunction(xList.get(i)) - yList.get(i), 2) > maxDeviation) {
                index = i;
                maxDeviation = Math.abs(getFunction(xList.get(i)) - yList.get(i));
            }
        }
        return index;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public List<Point> createFunctionForChart() {
        List<Point> functionForChart = new ArrayList<>();
        double startX = xList.get(0);
        double step = (xList.get(xList.size() - 1) - startX) / 99;
        for (int i = 0; i < 100; i++) {
            double x = startX + step * i;
            double y = getFunction(x);
            functionForChart.add(new Point(x, y));
        }
        return functionForChart;
    }









}
