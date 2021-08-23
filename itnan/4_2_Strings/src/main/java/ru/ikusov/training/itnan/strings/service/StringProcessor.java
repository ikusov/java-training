package ru.ikusov.training.itnan.strings.service;

import java.util.*;

import static ru.ikusov.training.utils.MyMath.r;
import static ru.ikusov.training.utils.MyMath.rs;

public final class StringProcessor {
    private StringProcessor(){}

    public static String самаяДлиннаяСтрокаВМассиве(String... strings) {
        return Arrays.stream(strings)
                .reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2)
                .get();
    }

    public static boolean isPalindrome(String string) {
        String s = string.replace(" ", "");
        int length = s.length()-1;
        boolean isPalindrome = true;
        for (int i=0, j=length; i<=j; i++, j--) {
            isPalindrome = s.charAt(i) == s.charAt(j) && isPalindrome;
        }
        return isPalindrome;
    }

    public static String[] getRandomStrings(int quantity) {
        String[] randomStrings = new String[quantity];

        for (int i=0; i<quantity; i++) {
            randomStrings[i] = rs(r(30));
        }

        return randomStrings;
    }

    public static Map<String, Integer> wordFrequency(String text, String delimRegEx) {
        String[] words = text.split(delimRegEx);
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            wordMap.put(word, wordMap.containsKey(word) ? wordMap.get(word)+1 : 1);
        }
        return wordMap;
    }

    public static Map<Character, Integer> letterFrequency(String text) {
        char[] symbols = text.toCharArray();
        Map<Character, Integer> letterMap = new HashMap<>();
        for (char symbol : symbols) {
            symbol = Character.toLowerCase(symbol);
            if (Character.isLetter(symbol))
                letterMap.put(symbol, letterMap.containsKey(symbol) ? letterMap.get(symbol) + 1 : 1);
        }
        return letterMap;
    }

    public static String toString(String... strings) {
        return Arrays.stream(strings)
                .reduce((s1, s2) -> String.join("\n", s1, s2))
                .get();
    }
}
