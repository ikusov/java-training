package ru.ikusov.training.skillbox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Movements {
    private Map<String, Movement> movements;
    private List<String> categories;

    private Movements() {}

    public static Movements getInstance(String CSVFile, String categoryToId) throws IOException {
        Movements movements = new Movements();

        Path file = Paths.get(CSVFile);
        if(Files.notExists(file)) return null;

        List<String> content = Files.readAllLines(file);

        if (content.size()<=1) {
            throw new RuntimeException("CSV File does not contain any movements data!");
        }

        movements.categories = parseCSVString(content.get(0));
        movements.movements = new HashMap<>();

        if (!movements.categories.contains(categoryToId)) {
            throw new RuntimeException(String.format("Cannot find category name %s for using as ID!", categoryToId));
        }

        int categoryToIdNum = movements.categories.indexOf(categoryToId);

        for (int i=1; i<content.size(); i++) {
            List<String> line = parseCSVString(content.get(i));
            String id = line.get(categoryToIdNum);
            movements.movements.put(id, new Movement(line));
        }

        return movements;
    }

    public String toString() {
        return movements
                .values()
                .stream()
                .map(Movement::toString)
                .reduce((s1, s2) -> String.join("\n", s1, s2))
                .get();
    }

    public Map<String, Movement> getMovements() {
        return movements;
    }

    public List<String> getCategories() {
        return categories;
    }

    public static List<String> parseCSVString(String CSV_String) {
        List<String> data = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        char[] string = CSV_String.toCharArray();
        boolean wasQuote = false;

        for (int i=0; i<string.length; i++) {
            if (string[i] == '\"') {
                if(wasQuote) {
                    if (i+1 < string.length && string[i+1] == '\"') {
                        token.append('\"');
                        i++;
                        continue;
                    } else {
                        wasQuote = false;
                        continue;
                    }
                } else {
                    wasQuote = true;
                    continue;
                }
            }
            if (string[i] == ',') {
                if(!wasQuote) {
                    data.add(token.toString());
                    token = new StringBuilder();
                    continue;
                }
            }
            token.append(string[i]);
        }
        data.add(token.toString());

        return data;
    }
}
