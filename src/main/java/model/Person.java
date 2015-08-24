package model;

import model.Positions.APosition;

import java.util.Map;

/**
 * Класс Сотрудник
 */
public class Person extends Thread {
    private final String personName; //имя сотрудника
    private volatile boolean isBusy; //проверка занят ли сотрудник
    private volatile boolean isTask;
    private volatile boolean stopWork;

    private float workHoursPerDay; //кол-во рабочих часов в день
    private float amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private Map<Position, APosition> listPositions; //список должностей

    private String task; //распоряжение к выполнению
    private float workHours; //счетчик рабочих часов


    public Person(String name) {
        this.personName = name;
        this.isBusy = false;
        this.isTask = false;
        this.stopWork = false;
    }

    /**
     * Метод запускает выполнение распоряжения (только если сотрудник не директор)
     * @param task распоряжение
     */
    public void performTask(Position position, String task) {
        this.task = task;
        this.isTask = true;
        listPositions.get(position).getToWork();
        //передает информацию в отчет о проделанной работе
    }

    @Override
    public void run() {
        workHours = workHoursPerDay;
        while(workHours > 0) {
            while (!isTask && !stopWork) Thread.yield(); //ждем получения задания или конца рабочего дня
            if (isTask) { //если есть задание
                this.isBusy = true; //сотрудник занят
                try {
                    Thread.sleep((long) amountHoursOneInstructions); //время выполнения задания

                    //выполняем распоряжение

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                workHours -= amountHoursOneInstructions; //высчитываем отработанные часы
                isBusy = false; //сотрудник освободился
                isTask = false; //задание выполнено
            } else if (stopWork) break; //если рабочий день закончился - останавливаем поток
        }
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
     *
     * @return готов ли сотрудник выполнить распоряжение
     */
    public boolean isWork() {
        return amountHoursOneInstructions <= workHours;
    }

    /**
     * Метод считает заработанную сумму со всех должностей
     * @return сумму зарплаты
     */
    public double paySalary() {
        double salary = 0;
        for (Map.Entry<Position, APosition> positionEntry : listPositions.entrySet())
            salary += positionEntry.getValue().paySalary();
        //передает информацию в отчет о выплаченной з/п
        return salary;
    }

    public void passReport(String report) {
        //передает информацию в отчет о проделанной работе
    }

    public void setIsTask(boolean isTask) {
        this.isTask = isTask;
    }

    public void setStopWork(boolean stopWork) {
        this.stopWork = stopWork;
    }

    public String getPersonName() {
        return personName;
    }

    public void setWorkHoursPerDay(float workHoursPerDay) {
        this.workHoursPerDay = workHoursPerDay;
    }

    public float getWorkHoursPerDay() {
        return workHoursPerDay;
    }

    public void setAmountHoursOneInstructions(float amountHoursOneInstructions) {
        this.amountHoursOneInstructions = amountHoursOneInstructions;
    }

    public float getAmountHoursOneInstructions() {
        return amountHoursOneInstructions;
    }

    public void setListPositions(Map<Position, APosition> listPositions) {
        this.listPositions = listPositions;
    }

    public Map<Position, APosition> getListPositions() {
        return listPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (Float.compare(person.workHoursPerDay, workHoursPerDay) != 0) return false;
        return !(personName != null ? !personName.equals(person.personName) : person.personName != null);

    }

    @Override
    public int hashCode() {
        int result = personName != null ? personName.hashCode() : 0;
        result = 31 * result + (workHoursPerDay != +0.0f ? Float.floatToIntBits(workHoursPerDay) : 0);
        return result;
    }
}
