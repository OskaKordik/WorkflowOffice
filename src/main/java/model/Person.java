package model;

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
        this.isBusy = true;
        this.isTask = true;
        //передать управление должности
        //передает информацию в отчет о проделанной работе
    }

    @Override
    public void run() {
        int countTask = 0;
        workHours = workHoursPerDay;
        while(workHours > 0) {
            while (!isTask && !stopWork) Thread.yield(); //ждем получения задания или конца рабочего дня
            if (isTask) { //если есть задание
                this.isBusy = true;
                try {
                    Thread.sleep((long) amountHoursOneInstructions); //время выполнения задания
                    countTask++;
                    System.out.println("Я выполнил работу! " + countTask); //выполняем распоряжение
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                workHours -= amountHoursOneInstructions; //высчитываем отработанные часы
                isBusy = false; //сотрудник освободился
                isTask = false; //задание выполнено
            } else if (stopWork) {
                break; //если рабочий день закончился - останавливаем поток
            }
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

    public void paySalary() {
        //передает информацию в отчет о выплаченной з/п
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
}
