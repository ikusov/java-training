package ru.ikusov.training.trash;

public class MainClass {
    public static void main(String... ljsdflkj) {
        String s1 = "string1";
        String s2 = new String("string1");

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s1 == s2 = " + (s1 == s2));
        System.out.println("s1.equals(s2) = " + s1.equals(s2));
        System.out.println("s1.hashCode() = " + s1.hashCode());
        System.out.println("s2.hashCode() = " + s2.hashCode());

    }
}
