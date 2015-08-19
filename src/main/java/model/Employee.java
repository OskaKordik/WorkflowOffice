package model;

import java.util.List;

/**
 * Класс Сотрудник
 */
public class Employee implements Runnable {
    private final String name; //имя сотрудника
    private final float HOURLY_RATE; //почасовая ставка
    private boolean isBusy; //проверка занят ли сотрудник
    private double hoursWorked; //отработанные часы - для почасовой оплаты
    private int fixedRate; //фиксированная ставка
    protected double workHoursPerDay; //кол-во рабочих часов в день
    protected long amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private List<Position> positionList; //список должностей
    private Thread thread;
    private String nameTask;

    /**
     * Конструктор для создания сотрудника с почасовой оплатой
     * @param name Имя сотрудника
     * @param hourlyRate Почасовая ставка
     */
    public Employee(String name, float hourlyRate, List<Position> positionList) {
        this.name = name;
        this.HOURLY_RATE = hourlyRate;
        this.fixedRate = 0;
        this.isBusy = false;
        this.positionList = positionList;
        thread = new Thread(this);
        workHoursPerDay = (long) (Math.random() * 8);
        amountHoursOneInstructions = (long) (Math.random() * 2);
    }

    /**
     * Конструктор для создания сотрудника с фиксированной оплатой труда
     * @param name Имя сотрудника
     * @param fixedRate Фиксированная ставка
     */
    public Employee(String name, int fixedRate, List<Position> positionList) {
        this.name = name;
        this.HOURLY_RATE = 0;
        this.fixedRate = fixedRate;
        this.isBusy = false;
        this.positionList = positionList;
        thread = new Thread(this);
        workHoursPerDay = (long) (Math.random() * 8);
        amountHoursOneInstructions = (long) (Math.random() * 2);
    }

    /**
     * Метод проверяет входит ли данное распоряжение в должностные обязанности
     * если да - запускает выполнение распоряжения
     * @param task распоряжение
     * @param position должность
     */
    public void performTask(String task, Position position) {
        if (positionList.contains(position)) runTask(name);
    }

    private void runTask(String nameTask) {
        this.nameTask = nameTask;
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(String.format("Сотрудник %s приступил к выполнению распоряжения: %s", this.getEmployeeName(), this.nameTask));
        try {
            Thread.sleep(amountHoursOneInstructions);
        } catch (InterruptedException e) {
            System.out.println("Во время выполнения задания возникла ошибка: " + e.getMessage());
        }
        System.out.println(String.format("Сотрудник %s выполнил распоряжение: %s", this.getEmployeeName(), this.nameTask));
        hoursWorked += amountHoursOneInstructions;
        workHoursPerDay -= amountHoursOneInstructions;
    }

    /**
     * Метод возвращает имя сотрудника
     */
    public String getEmployeeName() {
        return name;
    }

    /**
     * Метод, проверяющий занят ли сотрудник выполнением распоряжения
     * @return возвращает true если сотрудник занят
     */
    public boolean isBusy() {
        return isBusy;
    }

    /**
     * Метод проверяет, что количество времени необходимое на выполнение распоряжения
     * не превышает количество оставшихся рабочих часов
     * @return готов ли сотрудник выполнить распоряжение
     */
    public boolean isWork() {
        return amountHoursOneInstructions <= workHoursPerDay;
    }

    /**
     * Возвращает количество отработанных часов
     * (запрашивается раз в неделю для расчета зарплаты)
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Метод обнуляет отработанные часы
     */
    private void resetHoursWorked() {
        hoursWorked = 0;
    }

    /**
     * Метод возвращает фиксированную ставку если она установлена
     * иначе расчитывает зарплату из почасовой ставки и количества отработанных часов
     */
    public double getWeekSalary() {
        return fixedRate != 0 ? fixedRate : hoursWorked * HOURLY_RATE;
    }

}
