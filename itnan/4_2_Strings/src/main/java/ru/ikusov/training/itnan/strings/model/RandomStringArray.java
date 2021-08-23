package ru.ikusov.training.itnan.strings.model;

import ru.ikusov.training.itnan.strings.service.StringProcessor;

public class RandomStringArray {
    private final String[] strings;
    public String[] getStrings() {
        return strings;
    }

    public RandomStringArray(int size) {
        strings = StringProcessor.getRandomStrings(size);
    }

    @Override
    public String toString() {
        return StringProcessor.toString(strings);
    }
}
