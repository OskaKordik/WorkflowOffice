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

    /**
     * Метод записывающий в отчет информацию о получении сотрудником зарплаты
     * @param salary
     */
    void reportSalary(double salary);
}
