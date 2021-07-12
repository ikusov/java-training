package ru.ikusov.training.skillbox;

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
     * @param s
     * @param terminator
     * @param <S>
     */
    public static<S> void p(S s, String terminator) {
        Color color = Color.BLACK;
        color.setRandomColor();
        System.out.print(color.coloredString(s + terminator));
    }

    public static<S> void p(S s) {
        p(s, "\n");
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
