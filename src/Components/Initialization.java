package Components;

import Utilities.Individual;

import java.util.ArrayList;
import java.util.Random;

public class Initialization {
    public ArrayList<Individual> definePopulation(ArrayList<Individual> individualsList, int numberIndividuals, int numberObjects) {
        for (int index = 0; index < numberIndividuals; index++) {
            individualsList.add(new Individual(drawObjects(numberObjects)));
        }

        return individualsList;
    }

    public ArrayList<Boolean> drawObjects(int numberObjects) {
        Random random = new Random();

        ArrayList<Boolean> objectsId = new ArrayList<>();
        for (int index = 0; index < numberObjects; index++) {
            objectsId.add(random.nextInt(100) < 35);
        }

        return objectsId;
    }
}
