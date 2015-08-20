package model.Positions;

import model.Employee;
import model.Person;

/**
 * Класс должности Менеджер
 */
public class Manager extends Person implements Employee {
    private float fixedRate; //фиксированная ставка

    public Manager(String name, double workHoursPerDay) {
        super(name, workHoursPerDay);
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

        workHoursPerDay -= amountHoursOneInstructions;
    }
}
