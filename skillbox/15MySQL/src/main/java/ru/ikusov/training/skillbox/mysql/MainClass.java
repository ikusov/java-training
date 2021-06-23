package ru.ikusov.training.skillbox.mysql;

import static ru.ikusov.training.utils.Console.p;

public class MainClass {
    private final static String driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String... ljsdf) {
            test1();
    }

    public static void test1() {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            p("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
