package Components;

import Utilities.Individual;
import Utilities.Object;

import java.util.ArrayList;
import java.util.Random;

public class Mutation {

    public Individual performMutation(Individual individual, ArrayList<Object> objectsList, String method) {
        Random random = new Random();

        switch (method) {
            case "Method 1" -> {
                for (int index = 0; index < individual.getObjects().size() * 35 / 100; index++) {
                    individual.setObjects(random.nextInt(individual.getObjects().size()), !individual.getObjects(index));
                }
            }

            case "Method 2" -> {
                objectsList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

                for (int index = 0; index < individual.getObjects().size() * 35 / 100; index++) {

                    if (objectsList.get(index).getId() < individual.getObjects().size()) {
                        individual.setObjects(objectsList.get(index).getId(), true);
                    }
                }
            }

            case "Method 3" -> {
                for (int index = 0; index < individual.getObjects().size(); index++) {
                    individual.setObjects(random.nextInt(individual.getObjects().size()), !individual.getObjects(index));
                }
            }
        }

        return individual;
    }
}
