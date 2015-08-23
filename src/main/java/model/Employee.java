package model;

/**
 * Интерфейс сотрудников с фиксированной ставкой
 */
public interface Employee {

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
}
