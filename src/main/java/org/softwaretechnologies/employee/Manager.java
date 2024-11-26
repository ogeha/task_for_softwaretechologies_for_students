package org.softwaretechnologies.employee;

import java.time.YearMonth;

public class Manager extends Employee{

    public Manager(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        if (month % 2 != 0){
            return baseSalary/2;
        }
        return baseSalary;
    }
}
