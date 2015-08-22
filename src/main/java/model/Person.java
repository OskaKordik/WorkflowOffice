package model;

/**
 * Класс Сотрудник
 */
public class Person extends Thread {
    private final String name; //имя сотрудника
    private boolean isBusy; //проверка занят ли сотрудник
    private float workHoursPerDay; //кол-во рабочих часов в день
    private float workHours; //счетчик рабочих часов
    protected float amountHoursOneInstructions; //кол-во часов на выполнение одного задания
    private String task; //распоряжение к выполнению


    public Person(String name) {
        this.name = name;
        this.isBusy = false;
    }

    /**
     * Метод запускает выполнение распоряжения (только если сотрудник не директор)
     * @param task распоряжение
     */
    public void performTask(Position position, String task) {

        this.task = task;
        this.isBusy = true;

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

    public void setWorkHoursPerDay(float workHoursPerDay) {
        this.workHoursPerDay = workHoursPerDay;
    }

    public void setAmountHoursOneInstructions(float amountHoursOneInstructions) {
        this.amountHoursOneInstructions = amountHoursOneInstructions;
    }

    public void paySalary() {
        //передает информацию в отчет о выплаченной з/п
    }

    public void passReport(String report) {
        //передает информацию в отчет о проделанной работе
    }

}
