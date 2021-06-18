/*
Цель задания

Освоить лямбда-выражения.

Что нужно сделать

Задание выполняйте в проекте

09_AdvancedOOPFeatures/homework_9.1
В классе Main реализуйте метод sortBySalaryAndAlphabet(), который должен выполнить сортировку
переданного списка сотрудников по заработной плате и алфавиту.
То есть в начале списка будут минимальные значения зарплат, если значение зарплат одинаковое у сотрудников, то сотрудники с
одинаковой зарплатой должны быть отсортированы по Ф. И. О.
Проверьте ваш метод с помощью тестов.
Рекомендации:

Если вы используете репозиторий как многомодульный проект, вам потребуется настроить рабочую директорию модуля,
для того чтобы файл со списком сотрудников был доступен по указанному в пути коду: Настройка рабочей директории проекта в IDEA


Критерии оценки

«Зачёт» — тест успешно выполняется.

«На доработку» — задание не выполнено.



Материалы для изучения

Lambda-выражения в Java
Пример использования Lambda выражений


Цель заданий

Получить опыт работы со Stream API.


Задание №1

Задание выполняйте в проекте

09_AdvancedOOPFeatures/homework_9.2/Employees


Что нужно сделать

В классе Main реализуйте метод findEmployeeWithHighestSalary(), который должен выделить сотрудников,
пришедших в выбранном году, и среди них выявить сотрудника с максимальным значением заработной платы,
используя Stream API.
Проверьте ваш метод с помощью теста.
Критерии оценки

«Зачёт» —  выполняется тест по нахождению сотрудника.

«На доработку» — задание не выполнено.

*/

package ru.ikusov.training.skillbox.company2;

import ru.ikusov.training.utils.Console;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.Linguistic.generateRussianName;
import static ru.ikusov.training.utils.MyMath.r;

public class Main {
    public static void main(String[] args) {
        Company company = new Company("Roga & Kopyta", 15_000_000, 13_000);
        int staffSize = 500, r;

        for (int i=0; i++<staffSize;
             company.hire(((r=r())>staffSize/5*3) ? new Operator(company) :
                     r>staffSize/10 ? new Manager(company) :
                     new TopManager(company)));

        List<Employee> staff = company.getTopSalaryStaff(30);

        p("Staff before sortage:");
        printStaff(staff);

        sortBySalariesAndAlphabet(staff);
        p("\nStaff after sortage:");
        printStaff(staff);

        p("\nTop salaries for employees hired at years:");
//        IntStream.range(2010, 2021).boxed()
        Stream.iterate(2010, y -> y+1)
                .limit(11)
//        Stream.iterate(2010, y -> y < 2021, y -> y+1)
//        Stream.of(2012,2014,2016,2020)
                .map(y -> findEmployeeWithHighestSalary(company.getStaff(), y))
                .forEach(Console::p);
    }

    public static void sortBySalariesAndAlphabet(List<Employee> staff) {
        staff.sort((e1, e2) -> e1.getMonthSalary() != e2.getMonthSalary()
                                ? Integer.compare(e1.getMonthSalary(), e2.getMonthSalary())
                                : e1.getName().compareTo(e2.getName()));
    }

    public static void printStaff(List<Employee> staff) {
        staff.forEach(Console::p);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int hireYear) {
        Optional<Employee> identifier = staff.stream()
                            .filter(s -> s.getHireYear() == hireYear)
                            .max(Comparator.comparingInt(Employee::getMonthSalary));
        return identifier.isEmpty() ? null : identifier.get();
    }
}
