package model.Positions;

import model.Person;
import model.Сontractor;

/**
 * Класс должности Тестировщик
 */
public class Tester extends Person implements Сontractor {
    private float hourlyRate; //почасовая ставка
    private double hoursWorked; //отработанные часы - для почасовой оплаты


    public Tester(String name, double workHoursPerDay) {
        super(name, workHoursPerDay);
    }

    @Override
    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calcSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public void reportSalary(double salary) {
        System.out.println("Я получил зарплату! Аж : " + salary);
        hoursWorked = 0;
    }

    @Override
    public void run() {

        hoursWorked += amountHoursOneInstructions;
        workHoursPerDay -= amountHoursOneInstructions;
    }
}
