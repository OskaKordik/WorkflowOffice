package model.Positions;

import controller.Company;
import model.Employee;
import model.Person;
import model.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс должности Директор
 */
public class Director extends Person implements Employee {
    private float fixedRate; //фиксированная ставка
    private Map<Position, String> tasks; //список должностных обязанностей
    private List<Person> personList; //список работников

    public Director(String name, double workHoursPerDay) {
        super(name, workHoursPerDay);
        tasks = new HashMap<>();
        tasks.put(Position.Programmer, "писать код");
        tasks.put(Position.Designer, "рисовать макет");
        tasks.put(Position.Tester, "тестировать программу");
        tasks.put(Position.Manager, "продавать услуги");
        tasks.put(Position.Accountant, "составить отчетность");

        amountHoursOneInstructions = 10; //один час
    }

    @Override
    public void setFixedRate(float fixedRate) {
        this.fixedRate = fixedRate;
    }

    @Override
    public void paySalary() {
        reportSalary(getFixedRate());
    }

    @Override
    public float getFixedRate() {
        return fixedRate;
    }

    @Override
    public void reportSalary(float salary) {
        System.out.println("Я получил зарплату! Аж : " + salary);
    }

    public void getToWork() {
        this.personList = Company.getPersonList();
        for (Person person : personList) {
            if (person.getPositionList().contains(Position.Accountant)) { //например задание бухгалтеру
                if (!person.isBusy() && person.isWork()) {
                    person.performTask(tasks.get(Position.Accountant));
                }
            }
            //если для выполнения задания нет свободных сотрудников
            //нанимается фрилансер
        }
    }

    @Override
    public void run() {
        //каждый час раздает всем инструкции
        while (workHoursPerDay > 0) {

            workHoursPerDay -= amountHoursOneInstructions;
        }
    }
}
