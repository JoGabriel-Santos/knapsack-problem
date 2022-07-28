package Components;

import Utilities.Individual;

import java.util.ArrayList;
import java.util.Random;

public class Selection {
    public ArrayList<Individual> defineRanking(ArrayList<Individual> individualsList, Integer populationSize, String method) {
        ArrayList<Individual> classifiedIndividuals = new ArrayList<>();
        Random random = new Random();

        switch (method) {
            case "Method 1" -> {
                int total = individualsList.get(0).getScore();

                for (Individual individual : individualsList) {
                    if (random.nextInt((100) + 1) <= individual.getScore() * 100 / total) {
                        classifiedIndividuals.add(individual);
                    }

                    if (classifiedIndividuals.size() == populationSize / 2) break;
                }
            }

            case "Method 2" -> {
                int total = individualsList.get(0).getScore();

                for (Individual individual : individualsList) {

                    while (classifiedIndividuals.size() < individualsList.size() * 0.20) {
                        classifiedIndividuals.add(individual);
                    }

                    if (random.nextInt((100) + 1) <= individual.getScore() * 100 / total) {
                        classifiedIndividuals.add(individual);
                    }

                    if (classifiedIndividuals.size() == populationSize / 2) break;
                }
            }

            case "Method 3" -> {
                for (Individual individual : individualsList) {

                    classifiedIndividuals.add(individual);

                    if (classifiedIndividuals.size() == populationSize / 2) break;
                }
            }
        }

        return classifiedIndividuals;
    }
}
