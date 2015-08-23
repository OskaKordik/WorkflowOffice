package model;

/**
 * Интерфейс сотрудников с почасовой оплатой
 */
public interface Сontractor {

    /**
     * Метод, устанавливающий почасовую ставку
     * @param hourlyRate
     */
    void setHourlyRate(float hourlyRate);

    /**
     * Метод расчитывающий сумму зарплаты исходя из количества отработанных часов
     * и почасовой ставки
     * @return salary
     */
    double calcSalary();
}
