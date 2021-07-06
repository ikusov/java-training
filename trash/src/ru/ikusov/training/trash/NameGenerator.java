package ru.ikusov.training.trash;

import java.util.ArrayList;
import java.util.List;

class Name {
    public final static int NAMES_COUNT = 3;

    private String firstName;
    private String secondName;
    private String lastName;


    public Name(String firstName, String secondName, String lastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return String.format("%s %s %s", firstName, secondName, lastName);
    }
}

public class NameGenerator {
    public static void main(String... args) {

        List<Name> names = generateRussianNames();


        int namesSize = names.size(),
            outputSize = (int)(50*Math.random());
        System.out.printf("Generated %d unique names! Some of them:\n", namesSize);
        for (int i = 0; i<outputSize; i++) {
            System.out.println(names.get((int)(namesSize*Math.random())));
        }
    }

    public static List<Name> generateRussianNames() {
        String[] maleNameVariants = {"Александр", "Борис", "Фёдор", "Иван", "Пётр", "Михаил", "Денис", "Сидор", "Кирилл", "Федот",
                                "Владимир", "Данил", "Прохор", "Виктор"};
        String[] femaleNameVariants = {"Анна", "Алёна", "Виктория", "Галина", "Дарья", "Елена", "Жанна", "Зинаида", "Ирина", "Екатерина",
                                "Мария", "Наталья", "Ольга", "Светлана", "Татьяна", "Ульяна", "Фаина"};
        List<Name> names = new ArrayList<>();
        for (String mn1 : maleNameVariants) {
            for (String mn2 : maleNameVariants) {
                for (String mn3 : maleNameVariants) {
                    names.add(new Name(mn3.replace("Сидор", "Юрий"),

                            mn2.replace("Пётр", "Петр")
                                    .replace("Михаил", "Михайл")
                                    + "ович",

                            mn1.replace("Пётр", "Петр")
                                    .replace("Михаил", "Михайл")
                                    + "ов"
                    ));
                }
                for (String mn3 : femaleNameVariants) {
                    names.add(new Name(mn3,

                            mn2.replace("Пётр", "Петр")
                                    .replace("Михаил", "Михайл")
                                    + "овна",

                            mn1.replace("Пётр", "Петр")
                                    .replace("Михаил", "Михайл")
                                    + "ова"
                    ));
                }

            }
        }
        return names;
    }
}