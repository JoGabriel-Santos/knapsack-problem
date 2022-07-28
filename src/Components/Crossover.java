package Components;

import Utilities.Individual;
import Utilities.Object;

import java.util.ArrayList;
import java.util.Random;

public class Crossover {

    public ArrayList<Individual> performCrossover(ArrayList<Individual> classifiedIndividuals, ArrayList<Object> objectsList, int numberObjects, String method) {
        ArrayList<Individual> newPopulation = new ArrayList<>();

        Random random = new Random();

        switch (method) {
            case "Method 1" -> {
                while (newPopulation.size() < classifiedIndividuals.size() * 2) {
                    Individual randomIndividual_1 = classifiedIndividuals.get(random.nextInt(classifiedIndividuals.size()));
                    Individual randomIndividual_2 = classifiedIndividuals.get(random.nextInt(classifiedIndividuals.size()));

                    newPopulation.add(createNewIndividual(randomIndividual_1, randomIndividual_2, objectsList, numberObjects, method));
                }
            }

            case "Method 2" -> {
                while (newPopulation.size() < classifiedIndividuals.size() * 2) {

                    Individual randomIndividual_1, randomIndividual_2;

                    int index = 0;
                    while (newPopulation.size() < classifiedIndividuals.size() * 0.20) {
                        randomIndividual_1 = classifiedIndividuals.get(index);
                        randomIndividual_2 = classifiedIndividuals.get(index + 1);

                        newPopulation.add(createNewIndividual(randomIndividual_1, randomIndividual_2, objectsList, numberObjects, method));
                        index += 2;
                    }

                    randomIndividual_1 = classifiedIndividuals.get(random.nextInt(classifiedIndividuals.size()));
                    randomIndividual_2 = classifiedIndividuals.get(random.nextInt(classifiedIndividuals.size()));

                    newPopulation.add(createNewIndividual(randomIndividual_1, randomIndividual_2, objectsList, numberObjects, method));
                }
            }

            case "Method 3" -> {
                for (int index = 0; index < classifiedIndividuals.size(); index += 2) {
                    Individual randomIndividual_1 = classifiedIndividuals.get(index);
                    Individual randomIndividual_2 = classifiedIndividuals.get(index + 1);

                    newPopulation.add(createNewIndividual(randomIndividual_1, randomIndividual_2, objectsList, numberObjects, method));
                }
            }
        }

        return newPopulation;
    }

    public Individual createNewIndividual(
            Individual randomIndividual_1, Individual randomIndividual_2, ArrayList<Object> objectsList, int numberObjects, String method) {

        ArrayList<Boolean> newIndividualObjects = new ArrayList<>();
        Mutation mutation = new Mutation();

        Random random = new Random();

        int randPosition = random.nextInt(numberObjects);
        for (int index = 0; index < randPosition; index++) {
            newIndividualObjects.add(randomIndividual_1.getObjects().get(index));
        }

        for (int index = randPosition; index < numberObjects; index++) {
            newIndividualObjects.add(randomIndividual_2.getObjects().get(index - randPosition));
        }

        Individual newIndividual = new Individual(newIndividualObjects);

        if (random.nextInt(100) < 35) {
            newIndividual = mutation.performMutation(newIndividual, objectsList, method);
        }

        return newIndividual;
    }
}
