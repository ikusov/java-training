package ru.ikusov.training.itnan.strings;

import ru.ikusov.training.itnan.strings.model.RandomStringArray;
import ru.ikusov.training.itnan.strings.service.StringBuilderX;
import ru.ikusov.training.itnan.strings.service.StringProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.Console.pf;
import static ru.ikusov.training.utils.MyMath.r;
import static ru.ikusov.training.utils.MyMath.rs;

public class MainClass {
    public static void main(String... lkjsdfoij) throws IOException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    //task 1: longest string in array
    public static void test1() {
        RandomStringArray strings = new RandomStringArray(20);
        p("Hello! Now we generated the next bulk of strings:\n" + strings);
        p("\n\nAnd the longest string in it is:\n" +
                StringProcessor.самаяДлиннаяСтрокаВМассиве(strings.getStrings()));
    }

    //task 2: checking string for being palindrome
    public static void test2() {

        int size = 1000_000_000;

        for (int i=0; i<size; i++) {
            String string = rs(8);
            if (StringProcessor.isPalindrome(string))
                p(string);
        }
    }

    //task 3: most frequent letters and words in text
    public static void test3() throws IOException {
        String englishText = Files.readString(Paths.get("english_text.txt"));
        String russianText = Files.readString(Paths.get("russian_text.txt"));

        Map<String, Integer> englishWordMap = StringProcessor.wordFrequency(englishText, "[^A-Za-z']+"),
                                russianWordMap = StringProcessor.wordFrequency(russianText, "[^А-Яа-я]+");
        Map<Character, Integer> englishLetterMap = StringProcessor.letterFrequency(englishText),
                                russianLetterMap = StringProcessor.letterFrequency(russianText);

        printMostFrequent(englishWordMap, "English", "words", 10);
        printMostFrequent(englishLetterMap, "English", "letters", 10);
        printMostFrequent(russianWordMap, "Russian", "words", 10);
        printMostFrequent(russianLetterMap, "Russian", "letters", 10);
    }

    public static<T> void printMostFrequent(Map<T, Integer> tokenMap,
                                         String textDescription,
                                         String tokenType,
                                         int num) {
        pf("%n%s text %d most frequent %s:", textDescription, num, tokenType);
        tokenMap.entrySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(num).forEach(e -> pf("%d: %s", e.getValue(), e.getKey()));
    }


    //task 4: undoable stringbulider implementation
    public static void test4() {
        StringBuilderX sbx = new StringBuilderX();
        pf("Created sbx:%n%s", sbx.append("Hello!"));
        pf("Deleted last char:%n%s", sbx.deleteCharAt(sbx.length() - 1));
        pf("Added world!:%n%s", sbx.append(" world!"));
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
        pf("Undo last operation:%n%s", sbx.undo());
    }
}
