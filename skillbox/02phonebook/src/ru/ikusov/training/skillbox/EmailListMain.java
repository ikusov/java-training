package ru.ikusov.training.skillbox;

import java.util.Scanner;
import java.util.TreeSet;

public class EmailListMain {
    public static void main(String... oijaver) {
        EmailList list = new EmailList();

        String command = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Email list generator. Commands:\n" +
                "LIST - prints list of e-mails\n" +
                "ADD <email> - adds email to e-mail list\n" +
                "EXIT - exit program");

        while (true) {
            command = sc.nextLine();
            if (command.split(" ")[0].equalsIgnoreCase("add")) {
                try {
                    list.add(command.split(" ")[1]);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            } else if(command.equalsIgnoreCase("list")) {
                String[] slist = list.list();
                for (String s : slist) {
                    System.out.println(s);
                }
            } else if(command.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.err.println("Unknown command: " + command);
            }
        }
    }
}

class EmailList {
    private TreeSet<String> list = new TreeSet<>();

    public void add(String s) throws IllegalArgumentException{
        String emailRegex = ".+@.+\\..+";
        if (!s.matches(emailRegex)) {
            throw new IllegalArgumentException("Illegal e-mail format: " +
                        s + "\n\"<name>@<server>.<domain> expected.");
        } else {
            list.add(s);
        }
    }

    public String[] list() {
        int size = this.list.size(), i=0;
        String[] list = new String[size];

        for (String e : this.list) {
            list[i++] = e;
        }

        return list;
    }
}
