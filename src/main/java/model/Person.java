package model;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс Сотрудник
 */
public class Person implements Runnable {
    private final String name; //имя сотрудника
    private boolean isBusy; //проверка занят ли сотрудник
    protected float workHoursPerDay; //кол-во рабочих часов в день
    protected float amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private Set<Position> positionList; //список должностей
    private String task; //распоряжение к выполнению

    private static final SecureRandom random = new SecureRandom(); // for random enums


    public Person(String name) {
        this.name = name;
        workHoursPerDay = random.nextInt(8) + 1; //проверить, что кол-во заданных рабочих часов не превышает 8
        amountHoursOneInstructions = random.nextFloat() + 1; //проверить работу, отредактировать
        isBusy = false;
        positionList = setRandomPositions();
    }

    /**
     * Метод запускает выполнение распоряжения
     * @param task распоряжение
     */
    public void performTask(String task) {
        this.task = task;
        this.isBusy = true;
        //передает информацию в отчет о проделланой работе
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
    public Set<Position> getPositionList() {
        return positionList;
    }

    /**
     * Метод задает сотруднику список должностей случайным образом
     */
    private Set<Position> setRandomPositions() {
        Set<Position> list = new HashSet<>();
        int amountPositions = (int) (Math.random() * 5 + 1); //количество должностей

        for (int i = 0; i < amountPositions; i++) {
            int x = random.nextInt(Position.values().length); //выбор случайной должности
            list.add(Position.values()[x]); //добавление в список должности
        }
        return list;
    }

    /**
     * Метод выплачивающий зарплату
     */
    public void paySalary() {
        //передает информацию в отчет о выплаченной з/п
    }

    /**
     * Метод, назначающий сотрудника на определенную должность
     * @param position
     */
    public void setPositionList(Position position) {
        positionList.add(position);
    }

    @Override
    public void run() {
        //работает в течении заданных часов
        while (workHoursPerDay > 0) {
            try {
                Thread.sleep((long) amountHoursOneInstructions);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            workHoursPerDay -= amountHoursOneInstructions;
        }
    }
}
