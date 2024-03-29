package ru.ikusov.training.utils;

import java.util.Scanner;

/**
 * Uninstanceable uninheritable static console utilities class
 */
public final class Console {
    private Console() {}

    public enum Color {
        BLACK(30), RED(31), GREEN(32), YELLOW(33), BLUE(34), PURPLE(35), LIGHTBLUE(36), WHITE(37);
        private int color;

        Color(int color) {
            this.color = color;
        }

        public int getColor() {
            return color;
        }

        public void setRandomColor() {
            this.color = (int)(Math.random()*7+30);
        }

        public String coloredString(String string) {
            return (char) 27 + "[" + color + "m" + string + (char) 27 + "[0m";
        }
    }

    /**
     * Prints funny colored string ended by terminator
     * @param s string for funny coloring
     * @param terminator terminator for the funny colored string
     * @param <S> object of any type will be casted to String using toString() methoda
     */
    public static<S> void p(S s, String terminator) {
        Console.Color color = Console.Color.BLACK;
        color.setRandomColor();
        System.out.print(color.coloredString(s + terminator));
    }

    /**
     * Prints funny colored line
     * @param s string for funny coloring
     * @param <S> type of the object to string casting and funny coloring
     */
    public static<S> void p(S s) {
        p(s, "\n");
    }

    /**
     * Prints funny colored formatted line. Invoke String.format, if questions pls rd oracle docos.
     * @param format formatted string
     * @param args args for the formatted string
     */
    public static void pf(String format, Object... args) {p(String.format(format, args));}

    /**
     * Just print a string terminated by terminator
     * @param s string to print
     * @param terminator terminator to terminate
     * @param <S> type of object to stringing (using widely known toString() methoda)
     */
    public static<S> void pt(S s, String terminator) {
        System.out.print(s + terminator);
    }

    /**
     * Just print a gray dull string terminated by new line
     * @param s string to print
     * @param <S> type of object to be stringed by toString()
     */
    public static<S> void pt(S s) {
        pt(s, "\n");
    }

    /**
     * Inputs spaced array of int numbers
     * @return entered array
     */
    public static int[] inputInts() {
        Scanner sc = new Scanner(System.in);

        p("Введите элементы массива через пробел: ", "");

        String[] inputArray = sc.nextLine().split(" +");

        int[] array = new int[inputArray.length];
        for (int i=0;
             i<array.length;
             array[i] = Integer.parseInt(inputArray[i++]));

        return array;
    }

    /**
     * Input just int number
     * @return entered int number
     */
    public static int inputInt() {
        Scanner sc = new Scanner(System.in);

        p("Введите целое число: ", "");

        return sc.nextInt();
    }
}
