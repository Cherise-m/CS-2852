/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:
 * Name: Cherise Malisa
 * Created:
 */


package malisac;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class creates and displays graphs
 * that represent the time taken by the arraylist and linkedlist to:
 * remove an element from the front of a benchmark, and get an element from the middle of the list
 */
public class Graphs extends Application {

    private static final double NS_TO_MS = Math.pow(10.0, -6);

    /**
     * records the time to call the remove method until the list is empty
     *
     * @param list      a list type string
     * @param nElements number of elements in the list
     * @return the time taken in ns
     */
    public static double removeFromFrontBenchmark(List<String> list, int nElements){
        list.clear();

        for (int x = 0; x < nElements; x++) {
            list.add("look");
        }
        final long startTime = System.nanoTime();
        for (int i = 0; i < nElements; i++) {
            list.remove(0);
        }

        final long endTime = System.nanoTime();
        final long elapsed = endTime - startTime;

        return elapsed * NS_TO_MS;
    }

    /**
     * records the time taken by the list to get the element at the middle
     * of the list 100000 times
     *
     * @param list      a list of type string
     * @param nElements number of elements in the list
     * @return time taken in ns
     */
    public static double getMiddleBenchmark(List<String> list, int nElements){
        list.clear();

        for (int x = 0; x < nElements; x++) {
            list.add("look");
        }
        final long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            list.get(nElements / 2);
        }

        final long endTime = System.nanoTime();
        final long elapsed = endTime - startTime;

        return elapsed * NS_TO_MS;
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Scatter Chart ");
        final NumberAxis xAxis = new NumberAxis(0, 420000, 20000);
        final NumberAxis yAxis = new NumberAxis(0, 20000, 500);
        final ScatterChart<Number, Number> sc = new
                ScatterChart<>(xAxis, yAxis);
        xAxis.setLabel("Number of Elements");
        yAxis.setLabel("Time in Milliseconds");
        sc.setTitle("Remove from front");

        ArrayList<String> list = new ArrayList<>();
        LinkedList<String> list2 = new LinkedList<>();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("ArrayList");
        series1.getData().add(new XYChart.Data(20000, removeFromFrontBenchmark(list, 20000)));
        series1.getData().add(new XYChart.Data(40000, removeFromFrontBenchmark(list, 40000)));
        series1.getData().add(new XYChart.Data(200000, removeFromFrontBenchmark(list, 200000)));
        series1.getData().add(new XYChart.Data(400000, removeFromFrontBenchmark(list, 400000)));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("LinkedList");
        series2.getData().add(new XYChart.Data(20000, removeFromFrontBenchmark(list2, 20000)));
        series2.getData().add(new XYChart.Data(40000, removeFromFrontBenchmark(list2, 40000)));
        series2.getData().add(new XYChart.Data(200000, removeFromFrontBenchmark(list2, 200000)));
        series2.getData().add(new XYChart.Data(400000, removeFromFrontBenchmark(list2, 400000)));


        final NumberAxis xAxis2 = new NumberAxis(0, 420000, 20000);
        final NumberAxis yAxis2 = new NumberAxis(0, 40000, 1000);
        final ScatterChart<Number, Number> sc2 = new
                ScatterChart<>(xAxis2, yAxis2);
        xAxis2.setLabel("Number of Elements");
        yAxis2.setLabel("Time in Milliseconds");
        sc2.setTitle("Get from Middle");

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("ArrayList");
        series3.getData().add(new XYChart.Data(20000, getMiddleBenchmark(list, 20000)));
        series3.getData().add(new XYChart.Data(40000, getMiddleBenchmark(list, 40000)));
        series3.getData().add(new XYChart.Data(200000, getMiddleBenchmark(list, 200000)));
        series3.getData().add(new XYChart.Data(400000, getMiddleBenchmark(list, 400000)));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("LinkedList");
        series4.getData().add(new XYChart.Data(20000, getMiddleBenchmark(list2, 20000)));
        series4.getData().add(new XYChart.Data(40000, getMiddleBenchmark(list2, 40000)));
        series4.getData().add(new XYChart.Data(200000, getMiddleBenchmark(list2, 200000)));
        series4.getData().add(new XYChart.Data(400000, getMiddleBenchmark(list2, 400000)));

        sc.getData().addAll(series1, series2);
        sc2.getData().addAll(series3, series4);

        VBox vbox = new VBox(sc, sc2);
        Scene scene = new Scene(vbox, 550, 650);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
