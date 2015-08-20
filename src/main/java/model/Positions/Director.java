package model.Positions;

import model.Employee;
import model.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс должности Директор
 */
public class Director extends Person implements Employee {
    private float fixedRate; //фиксированная ставка
    private Map<String, String> tasks; //список должностных обязанностей

    public Director(String name, double workHoursPerDay) {
        super(name, workHoursPerDay);
        tasks = new HashMap<>();
        tasks.put("Programmer", "писать код");
        tasks.put("Designer", "рисовать макет");
        tasks.put("Tester", "тестировать программу");
        tasks.put("Manager", "продавать услуги");
        tasks.put("Accountant", "составить отчетность");

        amountHoursOneInstructions = 10; //один час
    }

    @Override
    public void setFixedRate(float fixedRate) {
        this.fixedRate = fixedRate;
    }

    @Override
    public float getFixedRate() {
        return fixedRate;
    }

    @Override
    public void reportSalary(float salary) {
        System.out.println("Я получил зарплату! Аж : " + salary);
    }

    @Override
    public void run() {
        //каждый час раздает всем инструкции
        workHoursPerDay -= amountHoursOneInstructions;
    }
}
