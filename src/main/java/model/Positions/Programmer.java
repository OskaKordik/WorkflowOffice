package model.Positions;

import model.Person;
import model.Сontractor;

/**
 * Класс должности Программист
 */
public class Programmer extends Person implements Сontractor {
    private float hourlyRate; //почасовая ставка
    private double hoursWorked; //отработанные часы - для почасовой оплаты


    public Programmer(String name) {
        super(name);
        hoursWorked = 0;
    }

    @Override
    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void paySalary() {
        reportSalary(calcSalary());
        hoursWorked = 0;
    }

    @Override
    public double calcSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public void reportSalary(double salary) {
        //отчитывается о получении зарплаты
        hoursWorked = 0;
    }

    public void getToWork() {
        //выполняет свою работу
        hoursWorked += amountHoursOneInstructions;
    }
}
