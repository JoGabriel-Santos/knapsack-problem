package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadData {
    public ArrayList<Object> read(String name) throws IOException {
        StringBuilder file_content;
        try (FileReader file = new FileReader(name)) {
            BufferedReader reader = new BufferedReader(file);
            file_content = new StringBuilder();

            String line = reader.readLine();
            while (line != null) {
                file_content.append(line).append("\n");
                line = reader.readLine();
            }
        }

        return initializeList(file_content.toString());
    }

    public ArrayList<Object> initializeList(String file_content) {
        ArrayList<Object> objectsList = new ArrayList<>();

        String[] individuals = file_content.split("\n");
        if (!file_content.equals("")) {
            int index = 0;
            for (String individual : individuals) {
                String[] genes = individual.split(";");

                String weight = genes[1];
                String value = genes[2];

                Object newObject = new Object(index, Integer.parseInt(weight), Integer.parseInt(value));
                objectsList.add(newObject);

                index++;
            }
        }

        return objectsList;
    }
}
