package model;

import java.util.List;

/**
 * ����� ���������
 */
public class Employee implements Runnable {
    private final String name; //��� ����������
    private final float HOURLY_RATE; //��������� ������
    private boolean isBusy; //�������� ����� �� ���������
    private double hoursWorked; //������������ ���� - ��� ��������� ������
    private int fixedRate; //������������� ������
    protected double workHoursPerDay; //���-�� ������� ����� � ����
    protected long amountHoursOneInstructions; //���-�� ����� �� ���������� ������ �������
    private List<Position> positionList; //������ ����������
    private Thread thread;
    private String nameTask;

    /**
     * ����������� ��� �������� ���������� � ��������� �������
     * @param name ��� ����������
     * @param hourlyRate ��������� ������
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
     * ����������� ��� �������� ���������� � ������������� ������� �����
     * @param name ��� ����������
     * @param fixedRate ������������� ������
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
     * ����� ��������� ������ �� ������ ������������ � ����������� �����������
     * ���� �� - ��������� ���������� ������������
     * @param task ������������
     * @param position ���������
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
        System.out.println(String.format("��������� %s ��������� � ���������� ������������: %s", this.getEmployeeName(), this.nameTask));
        try {
            Thread.sleep(amountHoursOneInstructions);
        } catch (InterruptedException e) {
            System.out.println("�� ����� ���������� ������� �������� ������: " + e.getMessage());
        }
        System.out.println(String.format("��������� %s �������� ������������: %s", this.getEmployeeName(), this.nameTask));
        hoursWorked += amountHoursOneInstructions;
        workHoursPerDay -= amountHoursOneInstructions;
    }

    /**
     * ����� ���������� ��� ����������
     */
    public String getEmployeeName() {
        return name;
    }

    /**
     * �����, ����������� ����� �� ��������� ����������� ������������
     * @return ���������� true ���� ��������� �����
     */
    public boolean isBusy() {
        return isBusy;
    }

    /**
     * ����� ���������, ��� ���������� ������� ����������� �� ���������� ������������
     * �� ��������� ���������� ���������� ������� �����
     * @return ����� �� ��������� ��������� ������������
     */
    public boolean isWork() {
        return amountHoursOneInstructions <= workHoursPerDay;
    }

    /**
     * ���������� ���������� ������������ �����
     * (������������� ��� � ������ ��� ������� ��������)
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * ����� �������� ������������ ����
     */
    private void resetHoursWorked() {
        hoursWorked = 0;
    }

    /**
     * ����� ���������� ������������� ������ ���� ��� �����������
     * ����� ����������� �������� �� ��������� ������ � ���������� ������������ �����
     */
    public double getWeekSalary() {
        return fixedRate != 0 ? fixedRate : hoursWorked * HOURLY_RATE;
    }

}
