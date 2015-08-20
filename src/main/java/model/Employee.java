package model;

/**
 * Интерфейс сотрудников с фиксированной ставкой
 */
public interface Employee extends Runnable {

    /**
     * Метод, устанавливающий фиксированную ставку
     * @param fixedRate
     */
    void setFixedRate(float fixedRate);

    /**
     * Метод возвращает фиксированную ставку
     * @return fixedRate
     */
    float getFixedRate();

    /**
     * Метод записывающий в отчет информацию о получении сотрудником зарплаты
     * @param salary
     */
    void reportSalary(float salary);
}
