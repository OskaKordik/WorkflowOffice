package model;

import java.security.SecureRandom;
import java.util.List;

/**
 * Класс Сотрудник
 */
public class Person {
    private final String name; //имя сотрудника
    private boolean isBusy; //проверка занят ли сотрудник
    protected double workHoursPerDay; //кол-во рабочих часов в день
    protected long amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private List<Position> positionList; //список должностей
    private String task; //распоряжение к выполнению

    private static final SecureRandom random = new SecureRandom(); // for random enums


    public Person(String name, double workHoursPerDay) {
        this.name = name;
        this.workHoursPerDay = workHoursPerDay;
        amountHoursOneInstructions = (long) (Math.random() * 2 + 1); //проверить работу, отредактировать
        isBusy = false;
        setRandomPositions();
    }

    /**
     * Метод запускает выполнение распоряжения
     * @param task распоряжение
     */
    public void performTask(String task) {
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

    /**
     * Метод возвращает список обязанностей
     * @return positionList
     */
    public List<Position> getPositionList() {
        return positionList;
    }

    /**
     * Метод задает сотруднику список должностей случайным образом
     */
    private void setRandomPositions() {
        int amountPositions = (int) (Math.random() * 6 + 1); //количество должностей

        for (int i = 0; i < amountPositions; i++) {
            int x = random.nextInt(Position.values().length); //выбор случайной должности
            positionList.add(Position.values()[x]); //добавление в список должности
        }

    }
}
