package Components;

import Utilities.Individual;
import Utilities.Object;

import java.util.ArrayList;

public class Assessment {
    public ArrayList<Individual> generateScore(ArrayList<Individual> individualsList, ArrayList<Object> objectsList) {
        for (Individual individual : individualsList) {
            int objectsWeight = 0, objectsValue = 0, index = 0;

            for (Boolean isObjectIncluded : individual.getObjects()) {
                objectsWeight += isObjectIncluded ? objectsList.get(index).getWeight() : 0;
                objectsValue += isObjectIncluded ? objectsList.get(index).getValue() : 0;

                index++;
            }

            individual.setCapacity(objectsWeight);

            if (objectsWeight > 250) {
                individual.setScore(Integer.MIN_VALUE);

            } else {
                individual.setScore(objectsValue);
            }
        }

        individualsList.sort((i1, i2) -> i2.getScore().compareTo(i1.getScore()));

        return individualsList;
    }
}
