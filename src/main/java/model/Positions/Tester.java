package model.Positions;

import model.Сontractor;

/**
 * Класс должности Тестировщик
 */
public class Tester extends APosition implements Сontractor {
    private float hourlyRate; //почасовая ставка
    private double hoursWorked; //отработанные часы - для почасовой оплаты


    public Tester(String name) {
        super(name);
        hoursWorked = 0;
    }

    /**
     * Метод, устанавливающий почасовую ставку
     * @param hourlyRate
     */
    @Override
    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * Метод расчитывающий сумму зарплаты исходя из количества отработанных часов
     * и почасовой ставки
     * @return salary
     */
    @Override
    public double calcSalary() {
        return hourlyRate * hoursWorked;
    }

    /**
     * Метод в котором выполняется работа должности
     */
    public void getToWork() {
        //выполняет свою работу
    }

    /**
     * Метод для получения зарплаты
     * @return сумму зарплаты
     */
    @Override
    public double paySalary() {
        double salary = calcSalary();
        //отчитывается о получении зарплаты
        hoursWorked = 0;
        return salary;
    }
}
