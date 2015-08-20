package model.Positions;

import controller.Company;
import model.Employee;
import model.Person;

/**
 * Класс должности Бухгалтер
 */
public class Accountant extends Person implements Employee {
    private float fixedRate; //фиксированная ставка

    public Accountant(String name, double workHoursPerDay) {
        super(name, workHoursPerDay);
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

    @Override
    public void run() {

        workHoursPerDay -= amountHoursOneInstructions;
    }

    /**
     * Начисление зарплаты всем сотрудникам компании
     */
    public void payWeekSalary() {
        for (Person person : Company.getPersonList()) {
            person.paySalary();
        }
    }
}
