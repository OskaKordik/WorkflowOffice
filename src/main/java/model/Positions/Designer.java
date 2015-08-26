package model.Positions;

import model.Сontractor;

/**
 * Класс должности Дизайнер
 */
public class Designer extends APosition implements Сontractor {
    private int hourlyRate; //почасовая ставка
    private double hoursWorked; //отработанные часы - для почасовой оплаты
    private float amountHoursOneInstructions; //кол-во часов на выполнение одного задания

    public Designer(String name) {
        super(name);
        hoursWorked = 0;
    }

    /**
     * Метод, устанавливающий почасовую ставку
     * @param hourlyRate
     */
    @Override
    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * Метод, устанавливающий время на выполнее задания
     * @param amountHoursOneInstructions
     */
    @Override
    public void setAmountHoursOneInstructions(float amountHoursOneInstructions) {
        this.amountHoursOneInstructions = amountHoursOneInstructions;
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
    @Override
    public void getToWork() {
        hoursWorked += amountHoursOneInstructions; //считаем отработанное время
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
