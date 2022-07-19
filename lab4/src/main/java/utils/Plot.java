package utils;

import data.Function;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.style.theme.GGPlot2Theme;

import java.awt.*;

public class Plot {
    private final Function function;

    public Plot(Function function) {
        this.function = function;
    }
    public void draw() {
        XYChart chart = new XYChartBuilder()
                .width(1024)
                .height(768)
                .title("Аппроксимация")
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();

        XYSeries points = chart.addSeries("Точки", function.getXList(), function.getYList());
        points.setLineStyle(SeriesLines.NONE);
        points.setLineColor(Color.red);

        getApproximation(function.getFunctions().size() - 1);
        XYSeries firstApproximation = chart.addSeries("Первая аппроксимация", function.getXList(), function.getYList());
        firstApproximation.setMarker(SeriesMarkers.NONE);
        firstApproximation.setLineStyle(SeriesLines.DASH_DASH);
        firstApproximation.setLineColor(Color.BLUE);

        getApproximation(function.getFunctions().size());
        XYSeries secondApproximation = chart.addSeries("Вторая аппроксимация", function.getXList(), function.getYList());
        secondApproximation.setMarker(SeriesMarkers.NONE);
        secondApproximation.setLineStyle(SeriesLines.DASH_DASH);
        secondApproximation.setLineColor(Color.GREEN);
        chart.getStyler().setTheme(new GGPlot2Theme());
        new SwingWrapper(chart).displayChart();
    }

    private void getApproximation(int number) {
        function.createLists(number);
    }
}
