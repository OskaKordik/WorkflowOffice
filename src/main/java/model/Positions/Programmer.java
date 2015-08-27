package model.Positions;

import model.Сontractor;

/**
 * Класс должности Программист
 */
public class Programmer extends APosition implements Сontractor {
    private int hourlyRate; //почасовая ставка
    private double hoursWorked; //отработанные часы - для почасовой оплаты
    private double allHoursWorked; //отработанные часы - для отчета
    private double salary; //зарплата
    private float amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private int countTasks = 0; //счетчик выполненных заданий

    public Programmer(String name) {
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
     * Метод, устанавливающий время на выполнениее задания
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
        allHoursWorked += amountHoursOneInstructions;
        countTasks++;
    }

    /**
     * Метод для получения зарплаты
     * @return сумму зарплаты
     */
    @Override
    public double paySalary() {
        salary += calcSalary();
        double weekSalary = calcSalary();
        hoursWorked = 0;
        return weekSalary;
    }

    @Override
    public double getAllHoursWorked() {
        return allHoursWorked;
    }

    @Override
    public void addAllHoursWorked(double allHoursWorked) {
        this.allHoursWorked = allHoursWorked;
    }

    public int getCountTasks() {
        return countTasks;
    }

    public double getSalary() {
        return salary;
    }
}
