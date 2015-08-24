package model.Positions;

import controller.DirectorsController;
import model.Employee;
import model.Person;
import model.Position;

import java.util.Map;
import java.util.Set;

/**
 * Класс должности Директор
 */
public class Director extends APosition implements Employee {
    private float fixedRate; //фиксированная ставка
    private Map<Position, String> taskList; //список распоряжений для сотрудников
    private Map<Person, Set<Position>> personList; //список сотрудников под руководством данного директора

    public Director(String name) {
        super(name);
    }

    /**
     * Метод, устанавливающий фиксированную ставку
     *
     * @param fixedRate
     */
    @Override
    public void setFixedRate(float fixedRate) {
        this.fixedRate = fixedRate;
    }

    /**
     * Метод в котором выполняется работа должности
     */
    @Override
    public void getToWork() {
        taskList = DirectorsController.getTaskList();
        personList = DirectorsController.getPersonListOtherDirectors();
        //раздает всем задания
        for (Map.Entry<Position, String> positionStringEntry : taskList.entrySet()) {
            Position currentPosition = positionStringEntry.getKey(); //текущая должность

            for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) {
                Person currentPerson = person.getKey(); //текущий сотрудник

                if (!currentPerson.isBusy() && currentPerson.isWork() && person.getValue().contains(currentPosition)) {
                    currentPerson.performTask(currentPosition, positionStringEntry.getValue()); //даем задание
                }
            }
        }
    }

    /**
     * Метод для получения зарплаты
     *
     * @return сумму зарплаты
     */
    @Override
    public double paySalary() {
        //отчитывается о получении зарплаты
        System.out.println("Я получил зарплату! ");
        return getFixedRate();
    }

    /**
     * Метод возвращает фиксированную ставку
     *
     * @return fixedRate
     */
    @Override
    public float getFixedRate() {
        return fixedRate;
    }
}
