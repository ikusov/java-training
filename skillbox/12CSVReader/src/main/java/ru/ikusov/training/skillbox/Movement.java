package ru.ikusov.training.skillbox;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movement {
    private List<String> data;

    Movement(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public String getOrganisation(int orgCategoryNum, String orgRegex) {
        Pattern orgRegexPattern = Pattern.compile(orgRegex);
        Matcher m = orgRegexPattern.matcher(data.get(orgCategoryNum));
        if (m.find())
            return m.group().trim();
        else
            return "";
    }

    public String toString() {
        return data.stream()
                .reduce((s1, s2) -> String.join(",", s1, s2))
                .get();
    }
}
