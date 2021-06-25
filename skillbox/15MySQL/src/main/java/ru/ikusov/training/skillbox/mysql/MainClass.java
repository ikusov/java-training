package ru.ikusov.training.skillbox.mysql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

import static ru.ikusov.training.utils.Console.p;

public class MainClass {
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String propertiesFileName = "database.properties";

    private final static String command = "SELECT course_name, " +
                                            "COUNT(*)/(MAX(MONTH(subscription_date))-MIN(MONTH(subscription_date))+1) " +
                                            "AS average_purchases " +
                                            "FROM purchaselist " +
                                            "GROUP BY course_name " +
                                            "ORDER BY course_name;";

    public static void main(String... ljsdf) throws SQLException {
            test1();
    }

    public static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(propertiesFileName)));
        return DriverManager.getConnection(properties.getProperty("url"),
                                            properties.getProperty("username"),
                                            properties.getProperty("password"));
    }

    public static void test1() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            p("Driver loading successful!");
        } catch (Exception e) {
            p("Driver loading failed!");
            e.printStackTrace();
        }

        try {
            connection = getConnection();
            p ("Connection to database successful!");
        } catch (Exception e) {
            p ("Connection to database failed!");
            e.printStackTrace();
        }

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(command);
        p("\nData received!\nMonth average purchase quantity of courses:");
        while (resultSet.next()) {
            p (String.format("%s: %.2f", resultSet.getString(1), resultSet.getFloat(2)));

        }
    }
}
