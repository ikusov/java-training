package ru.ikusov.training.skillbox.company2;

import ru.ikusov.training.utils.Linguistic;

import java.util.ArrayList;
import java.util.List;

import static ru.ikusov.training.utils.MyMath.r;

public class Company {
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
        return fixedSalary + r(fixedSalary/300)*100;
    }

    public int getManagerFixedSalary() {
        return getFixedSalary() + r(fixedSalary/200)*100;
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

    public ArrayList<Employee> getStaff() {
        ArrayList<Employee> staffNew = new ArrayList<>(List.copyOf(staff));
        return staffNew;
    }
}

interface Employee {
    int getMonthSalary();
    String getName();
    int getHireYear();
}

class Operator implements Employee {
    private final String name;
    int monthSalary;
    int hireYear;

    public Operator(String name, Company company, int hireYear) {
        this.name = name;
        this.hireYear = hireYear;
        if (company == null) {
            monthSalary = 0;
        } else {
            monthSalary = company.getFixedSalary();
        }
    }

    public Operator(Company company) {
        this(Linguistic.generateRussianName(), company, 2010 + r(11));
    }

    public String getName() {
        return name;
    }

    @Override
    public int getMonthSalary() {
        return monthSalary;
    }

    public int getHireYear() {
        return hireYear;
    }

    public String toString() {
        return String.format("%s %s hired at %d has salary %,drur",
                            this.getClass().getSimpleName(), name, hireYear, monthSalary);
    }
}

class Manager extends Operator {
    private int efficiency;

    public Manager(Company company) {
        super(company);
        if (company!=null) {
            monthSalary = company.getManagerFixedSalary();
            efficiency = 115000 + r(25)*1000;
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