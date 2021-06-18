package ru.ikusov.training.skillbox;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ru.ikusov.training.skillbox.Console.p;

public class CSVReaderMain {
    public static final String filename = "movementList.csv";
    public static final String categoryToId = "Референс проводки";

    public static final String orgRegex = "[\\/\\\\].+?  ";
    public static final int orgCategoryNum = 5;

    public static Movements movements;

    static {
        try {
            movements = Movements.getInstance(filename, categoryToId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... kjasg) throws IOException {
/*
        movements.getMovements().keySet()
                .stream().filter(key -> key.toLowerCase().contains("op1"))
                .forEach(key -> p(String.format("Key: %s\nOrganisation: %s\n%s: %s\n%s: %s\n%s: %s\n",
                        key,
                        movements.getMovements().get(key).getOrganisation(5, orgRegex),

                        movements.getCategories().get(3),
                        movements.getMovements().get(key).getData().get(3),

                        movements.getCategories().get(6),
                        movements.getMovements().get(key).getData().get(6),

                        movements.getCategories().get(7),
                        movements.getMovements().get(key).getData().get(7)
                        )));

 */
        Map<String, Double> sumIncomeForOrganisations = getSumForOrganisations("Приход");
        Map<String, Double> sumOutcomeForOrganisations = getSumForOrganisations("Расход");

        p(String.format("Сумма расходов: %,.2f руб.", getSum("Расход")));
        p(String.format("Сумма приходов: %,.2f руб.", getSum("Приход")));

        p("\nСумма приходов по организациям:");
        for (String organisation : sumIncomeForOrganisations.keySet()) {
            p(String.format("%s: %,.2f руб.", organisation, sumIncomeForOrganisations.get(organisation)));
        }

        p("\nСумма расходов по организациям:");
        for (String organisation : sumOutcomeForOrganisations.keySet()) {
            p(String.format("%s: %,.2f руб.", organisation, sumOutcomeForOrganisations.get(organisation)));
        }
    }

    public static Double getSum(String category) {
        int categoryIndex = movements.getCategories().indexOf(category);
        Double sum = 0d;
        try {
            for (Movement movement : movements.getMovements().values()) {
                sum += parseNumber(movement.getData().get(categoryIndex));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("No numeric data in selected category: " + category);
        }
        return sum;
    }

    public static Map<String, Double> getSumForOrganisations(String category) {
        Map<String, Double> sumForOrganisations = new HashMap<>();
        for (String organisation : getOrganisations()) {
            sumForOrganisations.put(organisation, getSumForOrganisation(organisation, category));
        }

        return sumForOrganisations;
    }

    public static Set<String> getOrganisations() {
        Set<String> organisations = new HashSet<>();
        for (Movement movement : movements.getMovements().values()) {
            organisations.add(movement.getOrganisation(orgCategoryNum, orgRegex));
        }
        return organisations;
    }

    public static Double getSumForOrganisation(String organisation, String category) {
        Double sum = 0d;
        int categoryIndex = movements.getCategories().indexOf(category);

        try {
            for (Movement movement : movements.getMovements().values()) {
                sum += movement.getOrganisation(orgCategoryNum, orgRegex).equals(organisation) ?
                        parseNumber(movement.getData().get(categoryIndex)) :
                        0;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("No integer data in selected category: " + category);
        }

        return sum;
    }

    public static double parseNumber(String number) {
        double num = 0;
        String number2 = number.replace("\"", "");
        number2 = number2.replace(",", ".");
        return Double.parseDouble(number2);
    }
}
