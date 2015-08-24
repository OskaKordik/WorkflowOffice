package model.Positions;

import model.Employee;

/**
 * Класс должности Директор
 */
public class Director extends APosition implements Employee {
    private float fixedRate; //фиксированная ставка

    public Director(String name) {
        super(name);
    }

    /**
     * Метод, устанавливающий фиксированную ставку
     * @param fixedRate
     */
    @Override
    public void setFixedRate(float fixedRate) {
        this.fixedRate = fixedRate;
    }

    /**
     * Метод в котором выполняется работа должности
     */
    @Override
    public void getToWork() {
        //выполняет свою работу
        System.out.println("Я выполняю работу! ");
        //раздает всем задания
    }

    /**
     * Метод для получения зарплаты
     * @return сумму зарплаты
     */
    @Override
    public double paySalary() {
        //отчитывается о получении зарплаты
        System.out.println("Я получил зарплату! ");
        return getFixedRate();
    }

    /**
     * Метод возвращает фиксированную ставку
     * @return fixedRate
     */
    @Override
    public float getFixedRate() {
        return fixedRate;
    }
}
