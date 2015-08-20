package model;

import java.util.List;

/**
 * Класс Сотрудник
 */
public class Person {
    private final String name; //имя сотрудника
    private boolean isBusy; //проверка занят ли сотрудник
    protected double workHoursPerDay; //кол-во рабочих часов в день
    protected long amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private List<Person> positionList; //список должностей
    private String task; //распоряжение к выполнению


    public Person(String name, double workHoursPerDay) {
        this.name = name;
        this.workHoursPerDay = workHoursPerDay;
        amountHoursOneInstructions = (long) (Math.random() * 2 + 1); //проверить работу, отредактировать
        isBusy = false;

        //здесь случайным образом задать список должностей
    }

    /**
     * Метод проверяет входит ли данное распоряжение в должностные обязанности
     * если да - запускает выполнение распоряжения
     * @param task распоряжение
     * @param position должность
     */
    public void performTask(String task, Person  position) {
        if (positionList.contains(position)) runTask(name);
    }

    private void runTask(String task) {
        this.task = task;
        this.isBusy = true;
    }

    /**
     * Метод возвращает имя сотрудника
     */
    public String getPersonName() {
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
}
