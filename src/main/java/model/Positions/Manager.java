package model.Positions;

import model.Employee;

/**
 * Класс должности Менеджер
 */
public class Manager extends APosition implements Employee {
    private int fixedRate; //фиксированная ставка
    private int countTasks = 0; //счетчик выполненных заданий
    private double salary; //зарплата
    private double allHoursWorked; //отработанные часы - для отчета

    public Manager(String name) {
        super(name);
    }

    /**
     * Метод, устанавливающий фиксированную ставку
     * @param fixedRate
     */
    @Override
    public void setFixedRate(int fixedRate) {
        this.fixedRate = fixedRate;
    }

    /**
     * Метод в котором выполняется работа должности
     */
    @Override
    public void getToWork() {
        countTasks++;
        //выполняет свою работу
    }

    /**
     * Метод для получения зарплаты
     * @return сумму зарплаты
     */
    @Override
    public double paySalary() {
        salary += getFixedRate();
        return getFixedRate();
    }

    @Override
    public double getAllHoursWorked() {
        return allHoursWorked;
    }

    @Override
    public void addAllHoursWorked(double allHoursWorked) {
        this.allHoursWorked += allHoursWorked;
    }

    /**
     * Метод возвращает фиксированную ставку
     * @return fixedRate
     */
    @Override
    public int getFixedRate() {
        return fixedRate;
    }

    public int getCountTasks() {
        return countTasks;
    }

    public double getSalary() {
        return salary;
    }
}
