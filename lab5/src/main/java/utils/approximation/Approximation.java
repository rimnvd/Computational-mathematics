package utils.approximation;

import data.Equation;
import data.Point;
import utils.GaussMethod;
import java.util.ArrayList;
import java.util.List;


public class Approximation {

    protected ArrayList<Double> xList;
    protected ArrayList<Double> yList;
    protected ArrayList<Double> lnxList;
    protected ArrayList<Double> lnyList;
    protected double[] coefficients;
    protected int type;
    protected final Equation equation;


    public void doApproximation() {
    }

    public Approximation(Equation equation, int type) {
        this.xList = equation.getXList();
        this.yList = equation.getYList(type);
        this.lnxList = equation.getLnxList();
        this.lnyList = equation.getLnyList(type);
        this.equation = equation;
    }

    public void changeType(int type) {
        this.type = type;
        this.yList = equation.getYList(type);
        this.lnyList = equation.getLnyList(type);
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

    public double[] getCoefficients() {
        return coefficients;
    }

    public List<Point> createFunctionForChart() {
        List<Point> functionForChart = new ArrayList<>();
        double startX = xList.get(0);
        double step = (xList.get(xList.size() - 1) - startX) / 49;
        for (int i = 0; i < 50; i++) {
            double x = startX + step * i;
            double y = getFunction(x);
            functionForChart.add(new Point(x, y));
        }
        return functionForChart;
    }









}
