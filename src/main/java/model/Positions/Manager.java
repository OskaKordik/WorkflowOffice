package model.Positions;

import model.Employee;

/**
 * Класс должности Менеджер
 */
public class Manager extends APosition implements Employee {
    private int fixedRate; //фиксированная ставка
    private int countTasks = 0; //счетчик выполненных заданий

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
        //отчитывается о получении зарплаты
        return getFixedRate();
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
}
