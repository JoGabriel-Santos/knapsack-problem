import Utilities.Graph;
import Utilities.Object;

import Components.Assessment;
import Components.Crossover;
import Components.Initialization;
import Components.Selection;
import Utilities.Individual;
import Utilities.LoadData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Main {
    private static final int NUM_GENERATIONS = 1000;
    private static final int NUM_INDIVIDUALS = 10000;

    private static final String METHOD = "Method 1";

    public static void main(String[] args) throws IOException {

        Initialization initialization = new Initialization();
        Assessment assessment = new Assessment();
        Selection selection = new Selection();
        Crossover crossover = new Crossover();

        ArrayList<Individual> individualsList = new ArrayList<>();

        LoadData loadData = new LoadData();
        ArrayList<Object> objectsList = loadData.read("Data.txt");

        ArrayList<Graph> graphData = new ArrayList<>();

        System.out.println("\n-----------------------------------------------------------\n");
        System.out.println(METHOD);
        for (int numberObjects = 20; numberObjects <= 100; numberObjects += 20) {
            individualsList.clear();

            System.out.println("Processing...   " + (numberObjects - 20) + "%");

            individualsList = initialization.definePopulation(individualsList, NUM_INDIVIDUALS, numberObjects);
            individualsList = assessment.generateScore(individualsList, objectsList);

            long start = System.currentTimeMillis();
            for (int index = 0; index < NUM_GENERATIONS; index++) {

                individualsList.removeIf(individual -> individual.getScore().equals(Integer.MIN_VALUE));

                if (individualsList.size() < NUM_INDIVIDUALS) {
                    individualsList.addAll(initialization.definePopulation(individualsList, NUM_INDIVIDUALS - individualsList.size(), numberObjects));
                    individualsList = assessment.generateScore(individualsList, objectsList);
                }

                individualsList = selection.defineRanking(individualsList, NUM_INDIVIDUALS, METHOD);
                individualsList = assessment.generateScore(individualsList, objectsList);
                individualsList = crossover.performCrossover(individualsList, objectsList, numberObjects, METHOD);
                individualsList = assessment.generateScore(individualsList, objectsList);
            }
            long elapsed = System.currentTimeMillis() - start;

            graphData.add(new Graph(elapsed, numberObjects));
        }

        generateGraph(graphData);
    }

    public static void printIndividualsData(ArrayList<Individual> individualsList, ArrayList<Object> objectsList) {
        for (Individual individual : individualsList) {
            System.out.println("\n-----------------------------------------------------------\n");
            System.out.println("Individual score: " + individual.getScore() + " | Capacity remaining: " + individual.getCapacity() + "\n");

            int index = 0;
            for (Boolean isObjectIncluded : individual.getObjects()) {
                if (isObjectIncluded) {
                    Object object = objectsList.get(index);
                    System.out.println("  ID: " + object.getId() + " | Weight: " + object.getWeight() + " | Value: " + object.getValue());
                }

                index++;
            }
        }

        System.out.println("\n-----------------------------------------------------------\n");
        System.out.println("Total of individuals: " + individualsList.size());
    }

    public static void generateGraph(ArrayList<Graph> graphData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Graph data : graphData) {
            dataset.addValue(data.getRuntime(), "Runtime", String.valueOf(data.getNumberItems()));
        }

        JFreeChart createGraph = ChartFactory.createLineChart(
                METHOD, "Quantity of items", "Runtime(ms)", dataset, PlotOrientation.VERTICAL, true, true, false);

        try {
            OutputStream png = new FileOutputStream("Graph.png");
            ChartUtilities.writeChartAsPNG(png, createGraph, 500, 400);
            png.close();

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        System.out.println("\nGenerated graph...");
    }
}
