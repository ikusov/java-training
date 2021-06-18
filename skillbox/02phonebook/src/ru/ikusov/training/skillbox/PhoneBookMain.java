/*
Цель задания

Научиться работать с коллекцией Map.

Что нужно сделать

Задание выполняйте в проекте

07_ArraysAndCollections/homework_7.4/PhoneBook

1. Напишите программу, которая будет работать как телефонная книга:

Если вводим новое имя, программа просит ввести номер телефона и запоминает его.
Если новый номер телефона — просит ввести имя и также запоминает.
Если вводим существующее имя или номер телефона, программа выводит телефон(ы) или имя абонента соответственно.
При вводе команды LIST программа печатает в консоль список всех абонентов в алфавитном порядке с номерами.
2. Определяйте имя и телефон с помощью регулярных выражений.

3. Подумайте, что выбрать в качестве ключа и значения для Map, и выберите лучший, по вашему мнению, вариант.
Опишите, какие минусы и плюсы видите в своём выборе.

4. Для работы с данными телефонной книги в проекте находится класс PhoneBook, который должен отвечать за
 хранение и работу с абонентами. Реализуйте все методы и проверьте класс с помощью существующих тестов.
 Вы можете добавлять дополнительные методы в класс.

Команды вводятся пользователем в консоль одной строкой.



Примеры работы с телефонной книгой (жирным шрифтом выделен ввод пользователя)
Введите номер, имя или команду:

Маша

Такого имени в телефонной книге нет.

Введите номер телефона для абонента “Маша”:

79001234567

Контакт сохранен!



Введите номер, имя или команду:

79007654321

Такого номера нет в телефонной книге.

Введите имя абонента для номера “79007654321”:

Маша

Контакт сохранен!



Введите номер, имя или команду:

Nfif@

Неверный формат ввода



Введите номер, имя или команду:

LIST

Маша - 79001234567, 79007654321



Критерии оценки

«Зачёт» — все тесты успешно выполняются, телефонная книга работает согласно условиям.

«На доработку» — задание не выполнено, тесты завершаются с ошибкой.

Материалы для изучения

Класс TreeMap, его устройство и способ применения.
Структуры данных в картинках. HashMap.
Структуры данных в картинках. LinkedHashMap.
A Guide to TreeMap in Java.
Документация по классу TreeMap.
Документация по классу HashMap.

*/
package ru.ikusov.training.skillbox;

import java.util.*;

import static ru.ikusov.training.utils.Console.*;

public class PhoneBookMain {
    public static void main(String... ljasfg) {
        String command = "";
        String numberRegex = "\\d+";
        String nameRegex = "[А-яA-z\\sЁё]+";

        Scanner sc = new Scanner(System.in);


        PhoneBook book = new PhoneBook();

        p("Phone book management tool. Please enter:\n"
                + "LIST - to print whole phone book\n"
                + "phone number to add new number or show subscriber name\n"
                + "subscriber name to add new subscriber or show subscriber phone number.\n"
                + "EXIT - to exit the program.");

        while (true) {
            command = sc.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                return;
            }
            if (command.equalsIgnoreCase("list")) {
                p(book);
                continue;
            }
            if (command.matches(nameRegex)) try {
                p(book.getNumbers(command));
            } catch (Exception e) {
                if (e instanceof NoSuchElementException ||
                    e instanceof NullPointerException) {
                    while (true) {
                        p("New subscriber added! Enter phone number for subscriber " + command + ":");
                        String name = command;
                        String number = sc.nextLine();
                        if (number.matches(numberRegex)) {
                            book.add(number, name);
                            break;
                        } else {
                            p("Bad command or filename!");
                        }
                    }
                }
            } finally {
                continue;
            }
            if (command.matches(numberRegex)) try {
                p(book.getName(command));
            } catch (Exception e) {
                if (e instanceof NoSuchElementException ||
                        e instanceof NullPointerException) {
                    while (true) {
                        p("New phone number added! Enter subscriber name for the number " + command + ":");
                        String name = sc.nextLine();
                        String number = command;
                        if (name.matches(nameRegex)) {
                            book.add(number, name);
                            break;
                        } else {
                            p("Bad command or filename!");
                        }
                    }
                }
            } finally {
                continue;
            }
            p("Bad format!");
        }
    }
}

class PhoneBook {
    private Map<String, String> book = new HashMap<>();

    public void add(String number, String name) {
        book.put(number, name);
    }

    public String toString() {

        Map<String, String> names = new HashMap<>();

        for (String number : book.keySet()) {
            String name = book.get(number);
            String number1 = names.get(name);
            number1 = number1 == null ? number : number1 + ", " + number;
            names.put(name, number1);
        }

        StringBuilder list = new StringBuilder();

        for (String e : names.keySet()) {
            list.append(e)
                    .append(" ")
                    .append(names.get(e))
                    .append("\n");
        }

        return list.toString();
    }

    public String getName(String number) {
        for (String curNumber : book.keySet()) {
            if (curNumber.equals(number)) {
                return book.get(curNumber);
            }
        }
        throw new NoSuchElementException();
    }

    public ArrayList<String> getNumbers(String name) {
        ArrayList<String> numbers = new ArrayList<>();
        for (String curNumber : book.keySet()) {
            if (book.get(curNumber).equalsIgnoreCase(name)) {
                numbers.add(curNumber);
            }
        }
        if (numbers.size() == 0) {
            throw new NoSuchElementException();
        }
        return numbers;
    }
}
