package model.Positions;

import model.Employee;
import model.Person;

/**
 * Класс должности Бухгалтер
 */
public class Accountant extends Person implements Employee {
    private float fixedRate; //фиксированная ставка

    public Accountant(String name) {
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
        //отчитывается о получении зарплаты
    }

    public void getToWork() {
        //выполняет свою работу
    }

    /**
     * Начисление зарплаты всем сотрудникам компании
     */
    public void payWeekSalary() {

    }
}
