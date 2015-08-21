package model;

import model.Positions.*;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс Сотрудник
 */
public class Person implements Runnable {
    private final String name; //имя сотрудника
    private boolean isBusy; //проверка занят ли сотрудник
    protected final float workHoursPerDay; //кол-во рабочих часов в день
    private float workHours; //счетчик рабочих часов
    protected float amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private Set<Position> positionList; //список должностей
    private String task; //распоряжение к выполнению

    private Programmer programmer;
    private Designer designer;
    private Director director;
    private Manager manager;
    private Tester tester;
    private Accountant accountant;

    private Thread currentThread;

    private static final SecureRandom random = new SecureRandom(); // for random enums


    public Person(String name) {
        this.name = name;
        workHoursPerDay = random.nextInt(8) + 1; //проверить, что кол-во заданных рабочих часов не превышает 8
        amountHoursOneInstructions = random.nextFloat() + 1; //проверить работу, отредактировать
        isBusy = false;
        positionList = setRandomPositions();
        createPositions(positionList);


    }

    /**
     * Метод запускает выполнение распоряжения (только если сотрудник не директор)
     *
     * @param task распоряжение
     */
    public void performTask(Position position, String task) {
        currentThread = new Thread(this);

        this.task = task;
        this.isBusy = true;

        /*
        switch (position.toString()) {
            case "Programmer":
                programmer.getToWork();
                break;
            case "Designer":
                designer.getToWork();
                break;
            case "Manager":
                manager.getToWork();
                break;
            case "Tester":
                tester.getToWork();
                break;
            case "Accountant":
                accountant.getToWork();
                break;
            default: break;
        }
        */
        currentThread.start();

        //передает информацию в отчет о проделанной работе
    }

    @Override
    public void run() {
        //выполянет работу
        workHours -= amountHoursOneInstructions;
        try {
            Thread.sleep((long) amountHoursOneInstructions); //время выполнение задания
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isBusy = false; //сотрудник освободился
    }

    /**
     * Метод возвращает имя сотрудника
     */
    public String getPersonName() {
        return name;
    }

    /**
     * Метод, проверяющий занят ли сотрудник выполнением распоряжения
     *
     * @return возвращает true если сотрудник занят
     */
    public boolean isBusy() {
        return isBusy;
    }

    /**
     * Метод проверяет, что количество времени необходимое на выполнение распоряжения
     * не превышает количество оставшихся рабочих часов
     *
     * @return готов ли сотрудник выполнить распоряжение
     */
    public boolean isWork() {
        return amountHoursOneInstructions <= workHours;
    }

    /**
     * Метод возвращает список обязанностей
     *
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
        int amountPositions = random.nextInt(6) + 1; //количество должностей

        for (int i = 0; i <= amountPositions; i++) {
            int x = random.nextInt(Position.values().length); //выбор случайной должности
            if (Position.values()[x] == Position.Director) { //если выпала должность директора
                list.clear(); //удаляем все предыдущие должности
                list.add(Position.values()[x]);
                amountHoursOneInstructions = 1;
                break;
            }
            list.add(Position.values()[x]); //добавление в список должности
        }
        return list;
    }

    private void createPositions(Set<Position> positionList) {
        if (positionList.contains(Position.Director))
            director = new Director(getPersonName() + " - " + Position.Director.toString());
        else {
            //создаем должности
            /*
            if (positionList.contains(Position.Programmer))
                programmer = new Programmer(getPersonName() + " - " + Position.Programmer.toString());
            if (positionList.contains(Position.Manager))
                manager = new Manager(getPersonName() + " - " + Position.Manager.toString());

            ...
            */
        }
    }



    /**
     * Метод, назначающий сотрудника на определенную должность
     *
     * @param position должность
     */
    public void setPositionList(Position position) {
        if (position == Position.Director) { //если выпала должность директора
            positionList.clear(); //удаляем все предыдущие должности
        }
        positionList.add(position);
    }

    /**
     * Метод выплачивающий зарплату
     */
    public void paySalary() {
        //передает информацию в отчет о выплаченной з/п
    }

}
