package ru.ikusov.training.skillbox.customerlist;

import ru.ikusov.training.utils.Console;

import java.util.Scanner;

import static ru.ikusov.training.utils.Console.p;

public class Main {
    public static void main(String... kjsfdkj) {
        CustomerStorage cs = CustomerStorage.getInstance();
        String[] command;
        Scanner sc = new Scanner(System.in);

        p("Console customer list service. Please enter one of the following commands:\n");
        help();

        for(;;) {
            command = sc.nextLine().split(" ", 2);
            try {
                if (command[0].equalsIgnoreCase("exit"))
                    break;

                else if (command[0].equalsIgnoreCase("add")) {
                    cs.addCustomer(command[1]);
                    p("Customer successfully added!");
                }

                else if (command[0].equalsIgnoreCase("remove")) {
                    cs.removeCustomer(command[1]);
                    p("Customer successfully removed!");
                }

                else if (command[0].equalsIgnoreCase("list")) {
                    cs.customerList().forEach(Console::p);
                }
                else throw new IllegalArgumentException("Bad command or filename!");
            } catch (RuntimeException e) {
                p(e.getMessage());
                help();
            }
        }
    }

    public static void help() {
        p("ADD Customer Name 1234567890 customer@email.me\n" +
                "REMOVE Customer Name\n" +
                "LIST\n" +
                "HELP\n" +
                "EXIT\n");
    }
}
