package model.Positions;

import model.Employee;
import model.Person;

/**
 * Класс должности Менеджер
 */
public class Manager extends Person implements Employee {
    private float fixedRate; //фиксированная ставка

    public Manager(String name) {
        super(name);
    }

    @Override
    public void setFixedRate(float fixedRate) {
        this.fixedRate = fixedRate;
    }

    @Override
    public void paySalary() {
        reportSalary(getFixedRate());
    }

    @Override
    public float getFixedRate() {
        return fixedRate;
    }

    @Override
    public void reportSalary(float salary) {
        System.out.println("Я получил зарплату! Аж : " + salary);
    }

    public void getToWork() {
        //выполняет свою работу
        workHoursPerDay -= amountHoursOneInstructions;
    }
}
