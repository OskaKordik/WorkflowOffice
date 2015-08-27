package model.Positions;

/**
 * Абстрактный класс должностей
 */
public abstract class APosition {
    String namePositions;

    public APosition(String name) {
        this.namePositions = name;
    }

    /**
     * Метод в котором выполняется работа должности
     */
    public abstract void getToWork();

    /**
     * Метод для получения зарплаты
     * @return сумму зарплаты
     */
    public abstract double paySalary();

    public String getNamePositions() {
        return namePositions;
    }

    public abstract double getAllHoursWorked();

    public abstract void addAllHoursWorked(double allHoursWorked);
}
