/*
Цель задания

Научиться работать с интерфейсами, абстрактными классами и взаимодействием классов.

Что нужно сделать

создать новый проект в папке 08_InheritanceAndPolymorphism/homework_8.4 и написать все требуемые классы
1. Создайте класс компании Company, содержащей сотрудников и реализующей методы:

найм одного сотрудника — hire(),
найм списка сотрудников – hireAll(),
увольнение сотрудника – fire(),
получение значения дохода компании – getIncome().
Аргументы и возвращаемое значение методов выберите на основании логики работы вашего приложения.

2. Создайте два метода, возвращающие список указанной длины (count). Они должны содержать сотрудников, отсортированных по убыванию и возрастанию заработной платы:

List<Employee> getTopSalaryStaff(int count),
List<Employee> getLowestSalaryStaff(int count).
3. Создайте классы сотрудников с информацией о зарплатах и условиями начисления зарплаты:

Manager — зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных для компании денег. Количество заработанных денег для компании генерируйте
случайным образом от 115 000 до 140 000 рублей.
TopManager — зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы, если доход компании более 10 млн рублей.
Operator — зарплата складывается только из фиксированной части.
Каждый класс сотрудника должен имплементировать интерфейс Employee. В интерфейсе Employee должен быть объявлен метод, возвращающий зарплату сотрудника:

getMonthSalary()
Аргументы и возвращаемое значение метода выберите в соответствии с логикой начисления зарплат. В интерфейсе при необходимости объявляйте необходимые методы.


Для демонстрации и тестирования работы ваших классов:

Создайте и наймите в компанию: 180 операторов Operator, 80 менеджеров по продажам Manager, 10 топ-менеджеров TopManager.
Распечатайте список из 10–15 самых высоких зарплат в компании.
Распечатайте список из 30 самых низких зарплат в компании.
Увольте 50% сотрудников.
Распечатайте список из 10–15 самых высоких зарплат в компании.
Распечатайте список из 30 самых низких зарплат в компании.

Примеры вывода списка зарплат

Список из пяти зарплат по убыванию:

230 000 руб.
178 000 руб.
165 870 руб.
123 000 руб.
117 900 руб.


Рекомендации

Сделайте возможным создавать разные экземпляры компании со своим списком сотрудников и доходом.
Чтобы получить данные компании внутри класса сотрудника, настройте хранение ссылки на Company и передавайте объект Company с помощью конструктора или сеттера.
Учтите, в методы получения списков зарплат могут передаваться значения count, превышающие количество сотрудников в компании, или отрицательные.
Критерии оценки

«Зачёт» — программа выполняет все требования.
«Незачёт» — задание не выполнено.



Материалы для изучения

Интерфейсы в Java.
Полиморфизм в Java.
Полиморфизм и наследование.
 */
package ru.ikusov.training.skillbox.company;

import ru.ikusov.training.utils.Linguistic;

import java.util.*;

import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.MyMath.r;
import static ru.ikusov.training.utils.MyMath.rs;

public class CompanyMain {
    public static void main(String... khsruiohaeguihgiadeskd) {
        Scanner sc = new Scanner(System.in);
        String command;

        p("Company emulation script! Commands:");
        p("CREATE <name> <income> <fixed salary> - to create new company.\nCREATE - to create new company with auto assigned properties.");
        p("HIRE <number> - to hire number of employees to the company.\nHIRE - to hire random number of employees.");
        p("FIRE <number> - to fire number of employees of the company.\nFIRE - to fire random number of employees.");
        p("TOP <number> - to print number of top salaried employees.");
        p("LOW <number> - to print number of low salaried employees.");
        p("EXIT - to exit program.");


        Company company = null;

        while (true) {
            command = sc.nextLine();
            if (command.equalsIgnoreCase("exit")) break;
            try {
                String[] token = command.split(" ");

                int income = 5_000_000 + r(10_000_000), salary = 10_000 + r(50_000);
                String name = rs();

                int toHire = 50 + r(200);

                if (token[0].equalsIgnoreCase("create")) {
                    company = new Company(name, income, salary);
                    p(String.format("New company %s with income %,d has just been created!", company.getName(), company.getIncome()));
                    continue;
                }

                if (token[0].equalsIgnoreCase("hire")) {
                    if (company == null) {
                        throw new IllegalArgumentException("Create company first!");
                    }
                    for (int i=0; i<toHire; i++) {
                        Employee employee = i < toHire/10
                                            ? new TopManager(company)
                                            : i < toHire/3
                                                ? new Manager(company)
                                                : new Operator(company);
                        company.hire(employee);
                    }
                    p(String.format("Hired %d new employees!", toHire));
                    continue;
                }

                if (token[0].equalsIgnoreCase("fire")) {
                    if (company == null) {
                        throw new IllegalArgumentException("Create company first!");
                    }

                    if (company.getStaffSize() == 0) {
                        throw new IllegalArgumentException("Hire employees first!");
                    }

                    int toFire = r(company.getStaffSize());
                    for (int i=0; i<toFire; i++) {
                        company.fireRandom();
                    }
                    p(String.format("Fired %d unhappy employees!", toFire));
                    continue;
                }

                if (token[0].equalsIgnoreCase("top")) {
                    if (company == null) {
                        throw new IllegalArgumentException("Create company first!");
                    }

                    if (company.getStaffSize() == 0) {
                        throw new IllegalArgumentException("Hire employees first!");
                    }

                    int q = 5 + r(15);
                    if (token.length>1)
                        q = Integer.parseInt(token[1]);
                    List<Employee> top = company.getTopSalaryStaff(q);
                    p(String.format("Top %d employees of the company %s:", q, company.getName()));
                    for (Employee employee : top) {
                        p(String.format("%s %s %,d", employee.getClass().getSimpleName(), employee.getName(), employee.getMonthSalary()));
                    }
                    continue;
                }

                if (token[0].equalsIgnoreCase("low")) {
                    if (company == null) {
                        throw new IllegalArgumentException("Create company first!");
                    }

                    if (company.getStaffSize() == 0) {
                        throw new IllegalArgumentException("Hire employees first!");
                    }

                    int q = 5 + r(15);
                    if (token.length>1)
                        q = Integer.parseInt(token[1]);
                    List<Employee> low = company.getLowestSalaryStaff(q);
                    p(String.format("Top %d losers of the company %s:", q, company.getName()));
                    for (Employee employee : low) {
                        p(String.format("%s %s %,d", employee.getClass().getSimpleName(), employee.getName(), employee.getMonthSalary()));
                    }
                    continue;
                }

                throw new IllegalArgumentException("Bad command or filename!");
            } catch (NumberFormatException e) {
                p("Invalid number!");
            } catch (IllegalArgumentException e) {
                p(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                p("");
            }
        }
    }
}

class Company {
    private final String name;
    private final List<Employee> staff;
    private final int income;

    private final int fixedSalary;

    Company(String name, int income, int fixedSalary) {
        staff = new ArrayList<>();
        this.name = name;
        this.income = income;
        this.fixedSalary = fixedSalary;
    }

    public void hire(Employee employee) {
        staff.add(employee);
    }

    public void hireAll(List<Employee> employees) {
        staff.addAll(employees);
    }

    public void fire(Employee employee) {
        staff.remove(employee);
    }

    public void fireRandom() {
        int i = r(staff.size());
        staff.remove(i);
    }

    public String getName() {
        return name;
    }

    public int getIncome() {
        return income;
    }

    public int getStaffSize() {
        return staff.size();
    }

    public int getFixedSalary() {
        return fixedSalary + r(fixedSalary/3);
    }

    public int getManagerFixedSalary() {
        return (int)(getFixedSalary()*1.5);
    }

    public int getTopManagerFixedSalary() {
        return getFixedSalary()*2;
    }

    private List<Employee> sortedStaff() {
        List<Employee> sortedStaff = new ArrayList<>(List.copyOf(staff));
        sortedStaff.sort((o1, o2) -> Integer.compare(o2.getMonthSalary(), o1.getMonthSalary()));
        return sortedStaff;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        if (count > staff.size() || count <= 0) {
            count = staff.size();
        }
        return sortedStaff().subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (count > staff.size() || count <= 0) {
            count = staff.size();
        }
        List<Employee> sortedStaff = sortedStaff();
        return sortedStaff.subList(sortedStaff.size()-count, sortedStaff.size());
    }
}

interface Employee {
    int getMonthSalary();
    String getName();
}

class Operator implements Employee {
    private final String name;
    int monthSalary;

    public Operator(String name, Company company) {
        this.name = name;
        if (company == null) {
            monthSalary = 0;
        } else {
            monthSalary = company.getFixedSalary() + r(company.getFixedSalary()/3);
        }
    }

    public Operator(Company company) {
        this(Linguistic.generateRussianName(), company);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getMonthSalary() {
        return monthSalary;
    }
}

class Manager extends Operator {
    private int efficiency;

    public Manager(Company company) {
        super(company);
        if (company!=null) {
            monthSalary = company.getManagerFixedSalary();
            efficiency = 115000 + (int)(25000*Math.random());
            monthSalary += efficiency*0.05;
        }
    }

    public int getEfficiency() {
        return efficiency;
    }
}

class TopManager extends Operator {
    public TopManager(Company company) {
        super(company);
        if (company!=null) {
            monthSalary = company.getTopManagerFixedSalary();
            monthSalary += company.getIncome() >= 10000000 ? monthSalary*1.5 : 0;
        }
    }
}