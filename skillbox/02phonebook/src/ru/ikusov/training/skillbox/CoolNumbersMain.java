/*
Цель задания

Научиться сортировать и искать элементы в коллекциях.

Что нужно сделать

Выполните задание в 07_NumbersStringsAndDates/homework_7.5/CoolNumbers



1. Реализуйте метод генерации «красивых» автомобильных номеров generateCoolNumbers() в классе CoolNumbers.
Используйте следующие правила генерации номеров:

XYZ — различный набор из списка разрешенных букв, N — цифры, R — регион (от 01 до 199);
XNNNYZR — пример: A111BC197, У777HC66.
В госномерах автомобилей в РФ используются следующие буквы: А, В, Е, К, М, Н, О, Р, С, Т, У, Х

В коллекции должно быть не менее 2 млн номеров.



2. Реализуйте методы поиска класса CoolNumbers по поиску номеров в каждой коллекции:

прямым перебором по ArrayList,
бинарным поиском по сортированному ArrayList,
поиском в HashSet,
поиском в TreeSet.


3. Измерьте и выведите длительность каждого метода поиска.

Формат вывода результатов поиска:

Поиск перебором: номер найден/не найден, поиск занял 34нс

Бинарный поиск: номер найден/не найден, поиск занял 34нс

Поиск в HashSet: номер найден/не найден, поиск занял 34нс

Поиск в TreeSet: номер найден/не найден, поиск занял 34нс



4. Напишите в форме ответа, какой поиск — самый быстрый, а какой — самый медленный.



В видео Поиск и сортировка неточно указана оценка результата работы метода бинарного поиска
Collections.binarySearch(). Метод возвращает int, если возвращаемое значение больше или равно нулю —
это означает, что элемент найден. Если возвращаемое значение int меньше нуля — элемент в коллекции не найден.


Рекомендации

сортировка не входит в учёт времени для бинарного поиска;
для детального сравнения методов поиска используйте время в наносекундах:   System.nanoTime()

Критерии оценки

«Зачёт» — при вводе в консоль автомобильного номера программа однозначно отвечает, найден ли номер
в каждой из коллекций. Все тесты успешно выполняются.

«На доработку» — задание не выполнено.



Материалы для изучения

Collections.sort () в Java с примерами.
Прямая и обратная сортировка ArrayList java.
Класс Collections.
О выборе структур данных для начинающих.





 */
package ru.ikusov.training.skillbox;

import java.util.*;

import static ru.ikusov.training.utils.Console.p;

public class CoolNumbersMain {
    public static void main(String... injuria) {
        //for pretty space digit group delimiter
        Locale.setDefault(Locale.FRENCH);

        //output of cool numbers generator
        p(CoolNumbers.generateCoolNumbers());

        //outputs of cool numbers searchers: for existing and non-existing number
        p(CoolNumbers.race(100, true));
        p(CoolNumbers.race(100, false));
    }
}

class CoolNumbers {

    //data sets
    private static List<String> arrayList = new ArrayList<>();
    private static List<String> sortedArrayList = new ArrayList<>();
    private static Set<String> hashSet = new HashSet<>();
    private static Set<String> treeSet = new TreeSet<>();

    //service variables
    private final static String LETTERS_STRING;
    public final static char[] LETTERS;
    public final static ArrayList<String> REGIONS;
    public final static int L_SIZE;
    public final static int R_SIZE;

    static {
        //initialization of service variables: allowed letters for car numbers in Russia
        LETTERS_STRING = "АВЕКМНОРСТУХ";
        LETTERS = LETTERS_STRING.toCharArray();
        L_SIZE = LETTERS.length;

        //initialization of service variables: region codes for car numbers in Russia
        REGIONS = new ArrayList<>();
        Collections.addAll(REGIONS, "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43",
                "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
                "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75",
                "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91",
                "92", "93", "94", "95", "96", "97", "98", "99", "102", "113", "116", "121", "122", "123", "124",
                "125", "126", "134", "136", "138", "142", "147", "150", "152", "154", "156", "159", "161", "163",
                "164", "173", "174", "177", "178", "186", "190", "193", "196", "197", "198", "199", "702", "750",
                "716", "761", "763", "774", "777", "790", "797", "799");
        R_SIZE = REGIONS.size();
    }


    //generators of each data set
    //every set is generated separately for duration determination purpose
    public static void generateCoolNumbersArrayList() {
        String coolNumber;
        for (int i = 0; i < L_SIZE; i++)
            for (int j = 0; j < L_SIZE; j++)
                for (int k = 0; k < L_SIZE; k++)
                    for (int r = 0; r < R_SIZE; r++)
                        for (int n = 1; n < 10; n++) {
                            coolNumber = "" + LETTERS[i] + n + n + n + LETTERS[j] + LETTERS[k] + REGIONS.get(r);
                            arrayList.add(coolNumber);
                        }
    }

    public static void generateCoolNumbersSortedArrayList() {
        String coolNumber;
        for (int i = 0; i < L_SIZE; i++)
            for (int j = 0; j < L_SIZE; j++)
                for (int k = 0; k < L_SIZE; k++)
                    for (int r = 0; r < R_SIZE; r++)
                        for (int n = 1; n < 10; n++) {
                            coolNumber = "" + LETTERS[i] + n + n + n + LETTERS[j] + LETTERS[k] + REGIONS.get(r);
                            sortedArrayList.add(coolNumber);
                        }
        Collections.sort(sortedArrayList);
    }

    public static void generateCoolNumbersHashSet() {
        String coolNumber;
        for (int i = 0; i < L_SIZE; i++)
            for (int j = 0; j < L_SIZE; j++)
                for (int k = 0; k < L_SIZE; k++)
                    for (int r = 0; r < R_SIZE; r++)
                        for (int n = 1; n < 10; n++) {
                            coolNumber = "" + LETTERS[i] + n + n + n + LETTERS[j] + LETTERS[k] + REGIONS.get(r);
                            hashSet.add(coolNumber);
                        }
    }

    public static void generateCoolNumbersTreeSet() {
        String coolNumber;
        for (int i = 0; i < L_SIZE; i++)
            for (int j = 0; j < L_SIZE; j++)
                for (int k = 0; k < L_SIZE; k++)
                    for (int r = 0; r < R_SIZE; r++)
                        for (int n = 1; n < 10; n++) {
                            coolNumber = "" + LETTERS[i] + n + n + n + LETTERS[j] + LETTERS[k] + REGIONS.get(r);
                            treeSet.add(coolNumber);
                        }
    }

    //cool numbers generator with every set generation duration output
    public static String generateCoolNumbers() {
        long dt;
        String message;
        dt = System.nanoTime();
        generateCoolNumbersArrayList();
        dt = System.nanoTime() - dt;
        message = String.format("Cool Numbers ARRAYLIST was generated in %,d nanos!\n", dt);

        dt = System.nanoTime();
        generateCoolNumbersSortedArrayList();
        dt = System.nanoTime() - dt;
        message += String.format("Cool Numbers SORTEDARRAYLIST was generated in %,d nanos!\n", dt);

        dt = System.nanoTime();
        generateCoolNumbersHashSet();
        dt = System.nanoTime() - dt;
        message += String.format("Cool Numbers HASHSET was generated in %,d nanos!\n", dt);

        dt = System.nanoTime();
        generateCoolNumbersTreeSet();
        dt = System.nanoTime() - dt;
        message += String.format("Cool Numbers TREESET was generated in %,d nanos!\n\n", dt);

        return message;
    }

    //single random cool number generator
    public static String generateRandom() {
        char[] letters = LETTERS_STRING.toCharArray();
        return "" + letters[(int)(Math.random()*letters.length)] +
                String.valueOf((int)(Math.random()*8 + 1)).repeat(3) +
                letters[(int)(Math.random()*letters.length)] +
                letters[(int)(Math.random()*letters.length)] +
                REGIONS.get((int)(Math.random()*REGIONS.size()));
    }

    //searching methods for every data set
    public static int findArrayList(String number) {
        for (int i=0; i<arrayList.size(); i++) {
            if (arrayList.get(i).equals(number))
                return i;
        }
        return -1;
    }

    public static int findSortedArrayList(String number) {
        return Collections.binarySearch(sortedArrayList, number);
    }

    public static boolean findHashSet(String number) {
        return hashSet.contains(number);
    }

    public static boolean findTreeSet(String number) {
        return treeSet.contains(number);
    }

    //searching duration test method
    public static String race(int attemptQuantity, boolean existingNumber) {
        String number, message;
        long dt, dtArrayList=0, dtSortedArrayList=0, dtHashSet=0, dtTreeSet=0,
                indexArrayList=0, indexSortedArrayList=0;
        int quantity;

        quantity = existingNumber ? attemptQuantity : 1;
        message = existingNumber
                ? String.format("After %d attempts of Cool Numbers Search:\n", attemptQuantity)
                : "After attempt of non-existing Cool Number Search:\n";

        for (int i=0; i<quantity; i++) {
            number = existingNumber ? generateRandom() : "Ы111ЫЫ11";
            dt = System.nanoTime();
            indexArrayList += findArrayList(number);
            dt = System.nanoTime() - dt;
            dtArrayList += dt;

            dt = System.nanoTime();
            indexSortedArrayList += findSortedArrayList(number);
            dt = System.nanoTime() - dt;
            dtSortedArrayList += dt;

            dt = System.nanoTime();
            findHashSet(number);
            dt = System.nanoTime() - dt;
            dtHashSet += dt;

            dt = System.nanoTime();
            findTreeSet(number);
            dt = System.nanoTime() - dt;
            dtTreeSet += dt;
        }
        dtArrayList/=quantity;
        indexArrayList/=quantity;
        dtSortedArrayList/=quantity;
        indexSortedArrayList/=quantity;
        dtHashSet/=quantity;
        dtTreeSet/=quantity;

        return  message +
                String.format("Average ARRAYLIST search time is %,d nanos, average ARRAYLIST index is %,d\n", dtArrayList, indexArrayList) +
                    String.format("Average SORTEDARRAYLIST search time is %,d nanos, average SORTEDARRAYLIST index is %,d\n", dtSortedArrayList, indexSortedArrayList) +
                String.format("Average HASHSET search time is %,d nanos\n", dtHashSet) +
                String.format("Average TREESET search time is %,d nanos\n\n", dtTreeSet);
    }
}
